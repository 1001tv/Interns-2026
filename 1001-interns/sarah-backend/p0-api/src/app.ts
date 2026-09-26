import express from 'express';
import type {Request, Response, NextFunction} from 'express';
//connecting all the files to the express app
import healthRouter from './routes/health';
import usersRouter from './routes/users';

const app = express();


app.use(express.json());

app.use(healthRouter); //registers the health route with the express app
app.use(usersRouter); //registers the users route with the express app


app.use((err: any, req: Request, res: Response, next : NextFunction) => {
    
    console.error(err);

    res.status(500).json({ error: 'Internal Server Error' });

});

export default app; //makes app available for import in other files



