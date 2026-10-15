# 1001 Internship App Backend

Prepared by: Sarah Sahm

This is the backend for our 1001 internship project, a streaming-style mobile application inspired by 1001.tv.

The backend handles authentication, user profiles, password recovery, favorites, and movie/TV data through TMDB.

## Stack

- Node.js
- TypeScript
- Express
- PostgreSQL
- Sequelize
- bcrypt
- JWT
- TMDB API
- dotenv

## Setup

Install dependencies:

```powershell
npm.cmd install
```

The development database is:


user-api

Run the migrations:

```powershell
npx.cmd sequelize-cli db:migrate --config config/config.js --migrations-path migrations
```

Current migrations create:

- `users`
- `favorites`

## Environment Variables

The project uses the following environment variables:

env

DB_HOST=localhost
DB_PORT=5432
DB_NAME=user-api
DB_USER=postgres
DB_PASSWORD=your-postgres-password

JWT_SECRET=your-jwt-secret

TMDB_ACCESS_TOKEN=your-tmdb-access-token


`.env.example` contains the required variable names.

## Running the Backend

```powershell
npm.cmd run dev
```

Default URL:

http://localhost:3000

## Authentication

Registration requires:

- name
- email
- password
- securityQuestion
- securityAnswer

Optional:

- age
- favoriteGenres

Passwords must be at least 8 characters.

Passwords and security answers are hashed using bcrypt.

Emails are unique.

Successful login returns a JWT token that currently expires after 7 days.

Protected routes use:

Authorization: Bearer <token>

## Security Questions

The available security questions are:

1. What city were you born in?
2. What was the name of your childhood best friend?
3. What was the name of your first pet?
4. What was the name of the street you grew up on?
5. What was the name of your favorite teacher?

They can also be retrieved from:

GET /auth/security-questions

## Profile

Each authenticated user has their own profile.

Editable fields:

- name
- age
- favoriteGenres

## Favorites

Favorites are stored separately from `favoriteGenres`.

Each saved favorite contains:

- userId
- tmdbId
- mediaType (`movie` or `tv`)

Favorites are tied to the authenticated user.

`GET /favorites` also retrieves the related TMDB information such as title, poster, overview, release date, and rating.

## TMDB

TMDB is used for:

- trending movies and TV shows
- search
- movie details
- TV show details

TMDB requests are handled through the backend.

## Endpoints


GET    /health

GET    /auth/security-questions
POST   /auth/register
POST   /auth/login
GET    /auth/validate

POST   /auth/forgot-password/question
POST   /auth/forgot-password/reset

GET    /profile
PUT    /profile

GET    /favorites
POST   /favorites
DELETE /favorites/:mediaType/:tmdbId

GET    /tmdb/trending
GET    /tmdb/search?query=
GET    /tmdb/movie/:id
GET    /tmdb/tv/:id


## Logout

Logout is handled client-side by removing the saved JWT token.