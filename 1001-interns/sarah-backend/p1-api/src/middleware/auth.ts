import type {Request, Response, NextFunction} from 'express';
import jwt from 'jsonwebtoken';

export interface AuthRequest extends Request { userId?: number;}

export function authenticateToken(
    req: AuthRequest,
    res: Response,
    next: NextFunction)
    { 
        const authHeader = req.headers['authorization'];

        if(!authHeader || !authHeader.startsWith('Bearer ')) 
            {
            return res.status(401).json({message: 'Unauthorized'});
        }

        const token = authHeader.split(' ')[1];

        const jwtSecret = process.env.JWT_SECRET;

        if(!jwtSecret) {
            return res.status(500).json({message: 'JWT secret is missing'});
        }

        try {
            const decoded = jwt.verify(token, jwtSecret) as { userId: number };
            
            req.userId = decoded.userId;
            next();
        } catch {
            return res.status(401).json({message: 'Unauthorized'});
            }
    }
           