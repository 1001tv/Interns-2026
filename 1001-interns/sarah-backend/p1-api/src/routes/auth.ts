import { Router } from 'express';
import bcrypt from 'bcrypt';
import jwt from 'jsonwebtoken';
import User from '../models/user';
import { authenticateToken, type AuthRequest } from '../middleware/auth';

const router = Router();


//list of the security questions used for resetting the password to account
const securityQuestions = [
  'What city were you born in?',
  'What was the name of your childhood best friend?',
  'What was the name of your first pet?',
  'What was the name of the street you grew up on?',
  'What was the name of your favorite teacher?'
];

router.get('/auth/security-questions', (req, res) => {
  return res.status(200).json({
    securityQuestions
  });
});

router.post('/auth/register', async (req, res) => {
  try {
    const {
      name,
      email,
      password,
      age,
      favoriteGenres,
      securityQuestion,
      securityAnswer
    } = req.body;

    if (!name || !email || !password || !securityQuestion || !securityAnswer) {
      return res.status(400).json({ message: 'Required fields are missing'});
    }

    if (password.length < 8) {
  return res.status(400).json({
    message: 'Password must be at least 8 characters'
  });
}

    if (!securityQuestions.includes(securityQuestion)) {
  return res.status(400).json({ message: 'Invalid security question'});
}

    const existingUser = await User.findOne({
      where: { email }
    });

    if (existingUser) {
      return res.status(409).json({
        message: 'Email is already registered'
      });
    }

    const passwordHash = await bcrypt.hash(password, 10);

    const securityAnswerHash = await bcrypt.hash(
      securityAnswer.trim().toLowerCase(),
      10
    );

    const newUser = await User.create({
      name,
      email,
      passwordHash,
      age: age ?? null,
      favoriteGenres: favoriteGenres ?? null,
      securityQuestion,
      securityAnswerHash
    });

    return res.status(201).json({
      message: 'Account created successfully',
      user: {
        id: newUser.get('id'),
        name: newUser.get('name'),
        email: newUser.get('email'),
        age: newUser.get('age'),
        favoriteGenres: newUser.get('favoriteGenres')
      }
    });

  } catch (error) {
    console.error(error);

    return res.status(500).json({
      message: 'Internal server error'
    });
  }
});

router.post('/auth/login', async (req, res) => {
  try {
    const { email, password } = req.body;

    if (!email || !password) {
      return res.status(400).json({
        message: 'Email and password are required'
      });
    }

    const user = await User.findOne({
      where: { email }
    });

    if (!user) {
      return res.status(401).json({
        message: 'Incorrect email or password'
      });
    }

    const passwordIsCorrect = await bcrypt.compare(
      password,
      user.get('passwordHash') as string
    );

    if (!passwordIsCorrect) {
      return res.status(401).json({ message: 'Incorrect email or password'});
    }

    const jwtSecret = process.env.JWT_SECRET;

    if (!jwtSecret) {
      return res.status(500).json({ message: 'JWT secret is missing'});
    }

    const token = jwt.sign(
      { userId: user.get('id') },
      jwtSecret,
      { expiresIn: '7d' }
    );

    return res.status(200).json({
      message: 'Login successful',
      token,
      user: {
        id: user.get('id'),
        name: user.get('name'),
        email: user.get('email')
      }
    });

  } catch (error) {
    console.error(error);

    return res.status(500).json({
      message: 'Internal server error'
    });
  }
});

router.get('/auth/validate', authenticateToken, async (req: AuthRequest, res) => {
  return res.status(200).json({
    message: 'Token is valid',
    userId: req.userId
  });
});


router.post('/auth/forgot-password/question', async (req, res) => {
  try {
    const { email } = req.body;

    if (!email) {
      return res.status(400).json({
        message: 'Email is required'
      });
    }

    const user = await User.findOne({
      where: { email }
    });

    if (!user) {
      return res.status(404).json({
        message: 'Account not found'
      });
    }

    return res.status(200).json({
      securityQuestion: user.get('securityQuestion')
    });

  } catch (error) {
    console.error(error);

    return res.status(500).json({
      message: 'Internal server error'
    });
  }
});


router.post('/auth/forgot-password/reset', async (req, res) => {
  try {
    const { email, securityAnswer, newPassword } = req.body;

    if (!email || !securityAnswer || !newPassword) {
      return res.status(400).json({
        message: 'Email, security answer and new password are required'
      });
    }

    if (newPassword.length < 8) {
  return res.status(400).json({
    message: 'Password must be at least 8 characters'
  });
}

    const user = await User.findOne({
      where: { email }
    });

    if (!user) {
      return res.status(404).json({
        message: 'Account not found'
      });
    }

    const answerIsCorrect = await bcrypt.compare(
      securityAnswer.trim().toLowerCase(),
      user.get('securityAnswerHash') as string
    );

    if (!answerIsCorrect) {
      return res.status(401).json({
        message: 'Incorrect security answer'
      });
    }

    const passwordHash = await bcrypt.hash(newPassword, 10);

    await user.update({
      passwordHash
    });

    return res.status(200).json({ message: 'Password reset successfully' });

  } catch (error) {
    console.error(error);

    return res.status(500).json({
      message: 'Internal server error'
    });
  }
});

export default router;