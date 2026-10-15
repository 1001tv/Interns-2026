import express from 'express';
import type {Request, Response, NextFunction} from 'express';

//connecting all the files to the app
import healthRouter from './routes/health';
import authRouter from './routes/auth';
import profileRouter from './routes/profile';
import favoritesRouter from './routes/favorites';
import tmdbRouter from './routes/tmdb';

const app = express();


app.use(express.json());

app.use(healthRouter);
app.use(authRouter);
app.use(profileRouter);
app.use(favoritesRouter);
app.use(tmdbRouter);

app.use((err: any, req: Request, res: Response, next : NextFunction) => {
    
    console.error(err);

    res.status(500).json({ error: 'Internal Server Error' });

});

export default app; //makes app available for import in other files



