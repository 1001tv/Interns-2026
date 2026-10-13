import { Router } from 'express';
import Favorite from '../models/favorite';
import { authenticateToken, type AuthRequest } from '../middleware/auth';

const router = Router();

const TMDB_BASE_URL = 'https://api.themoviedb.org/3';

function getTmdbHeaders() {
  const token = process.env.TMDB_ACCESS_TOKEN;

  if (!token) {
    throw new Error('TMDB access token is missing');
  }

  return {
    Authorization: `Bearer ${token}`,
    accept: 'application/json'
  };
}


// get all favorites for the logged-in user
router.get('/favorites', authenticateToken, async (req: AuthRequest, res) => {
  try {
    const favorites = await Favorite.findAll({
      where: {
        userId: req.userId
      },
      order: [['createdAt', 'DESC']]
    });

    const favoriteDetails = await Promise.all(
      favorites.map(async (favorite) => {
        const tmdbId = Number(favorite.get('tmdbId'));
        const mediaType = String(favorite.get('mediaType'));

        const response = await fetch(
          `${TMDB_BASE_URL}/${mediaType}/${tmdbId}`,
          {
            headers: getTmdbHeaders()
          }
        );

        if (!response.ok) {
          return {
            id: favorite.get('id'),
            tmdbId,
            mediaType
          };
        }

        const data: any = await response.json();

        return {
          id: favorite.get('id'),
          tmdbId,
          mediaType,

          title:
            mediaType === 'movie'
              ? data.title
              : data.name,

          overview: data.overview,

          posterPath: data.poster_path,

          posterUrl: data.poster_path
            ? `https://image.tmdb.org/t/p/w500${data.poster_path}`
            : null,

          backdropPath: data.backdrop_path,

          releaseDate:
            mediaType === 'movie'
              ? data.release_date
              : data.first_air_date,

          voteAverage: data.vote_average
        };
      })
    );

    return res.status(200).json({
      favorites: favoriteDetails
    });

  } catch (error) {
    console.error(error);

    return res.status(500).json({
      message: 'Internal server error'
    });
  }
});


// add a movie or series to favorites
router.post('/favorites', authenticateToken, async (req: AuthRequest, res) => {
  try {
    const { tmdbId, mediaType } = req.body;

    if (!tmdbId || !mediaType) {
      return res.status(400).json({
        message: 'tmdbId and mediaType are required'
      });
    }

    if (mediaType !== 'movie' && mediaType !== 'tv') {
      return res.status(400).json({
        message: 'mediaType must be movie or tv'
      });
    }

    const existingFavorite = await Favorite.findOne({
      where: {
        userId: req.userId,
        tmdbId,
        mediaType
      }
    });

    if (existingFavorite) {
      return res.status(409).json({
        message: 'Already in favorites'
      });
    }

    const favorite = await Favorite.create({
      userId: req.userId,
      tmdbId,
      mediaType
    });

    return res.status(201).json({
      message: 'Added to favorites',
      favorite
    });

  } catch (error) {
    console.error(error);

    return res.status(500).json({
      message: 'Internal server error'
    });
  }
});


// remove a movie or series from favorites
router.delete(
  '/favorites/:mediaType/:tmdbId',
  authenticateToken,
  async (req: AuthRequest, res) => {
    try {
      const { mediaType, tmdbId } = req.params;

      const favorite = await Favorite.findOne({
        where: {
          userId: req.userId,
          tmdbId: Number(tmdbId),
          mediaType
        }
      });

      if (!favorite) {
        return res.status(404).json({
          message: 'Favorite not found'
        });
      }

      await favorite.destroy();

      return res.status(200).json({
        message: 'Removed from favorites'
      });

    } catch (error) {
      console.error(error);

      return res.status(500).json({
        message: 'Internal server error'
      });
    }
  }
);

export default router;