import { Router } from 'express';
import User from '../models/user';
import { authenticateToken, type AuthRequest } from '../middleware/auth';

const router = Router();


router.get('/profile', authenticateToken, async (req: AuthRequest, res) => {
  try {
    const user = await User.findByPk(req.userId);

    if (!user) {
      return res.status(404).json({
        message: 'User not found'
      });
    }

    return res.status(200).json({
      user: {
        id: user.get('id'),
        name: user.get('name'),
        email: user.get('email'),
        age: user.get('age'),
        favoriteGenres: user.get('favoriteGenres')
      }
    });

  } catch (error) {
    console.error(error);

    return res.status(500).json({
      message: 'Internal server error'
    });
  }
});


router.put('/profile', authenticateToken, async (req: AuthRequest, res) => {
  try {
    const user = await User.findByPk(req.userId);

    if (!user) {
      return res.status(404).json({
        message: 'User not found'
      });
    }

    const { name, age, favoriteGenres } = req.body;

    await user.update({
      name: name ?? user.get('name'),
      age: age ?? user.get('age'),
      favoriteGenres: favoriteGenres ?? user.get('favoriteGenres')
    });

    return res.status(200).json({
      message: 'Profile updated successfully',
      user: {
        id: user.get('id'),
        name: user.get('name'),
        email: user.get('email'),
        age: user.get('age'),
        favoriteGenres: user.get('favoriteGenres')
      }
    });

  } catch (error) {
    console.error(error);

    return res.status(500).json({
      message: 'Internal server error'
    });
  }
});

export default router;