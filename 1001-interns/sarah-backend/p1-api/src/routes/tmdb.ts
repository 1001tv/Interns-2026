import { Router } from 'express';

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

// get trending movies and tv shows
router.get('/tmdb/trending', async (req, res) => {
  try {
    const response = await fetch(
      `${TMDB_BASE_URL}/trending/all/day`,
      {
        headers: getTmdbHeaders()
      }
    );

    if (!response.ok) {
      return res.status(response.status).json({
        message: 'Failed to get trending media from TMDB'
      });
    }

    const data = await response.json();

    return res.status(200).json(data);

  } catch (error) {
    console.error(error);

    return res.status(500).json({
      message: 'Internal server error'
    });
  }
});

// search movies and tv shows
router.get('/tmdb/search', async (req, res) => {
  try {
    const query = req.query.query;

    if (!query || typeof query !== 'string') {
      return res.status(400).json({
        message: 'Search query is required'
      });
    }

    const response = await fetch(
      `${TMDB_BASE_URL}/search/multi?query=${encodeURIComponent(query)}`,
      {
        headers: getTmdbHeaders()
      }
    );

    if (!response.ok) {
      return res.status(response.status).json({
        message: 'Failed to search TMDB'
      });
    }

    const data = await response.json();

    return res.status(200).json(data);

  } catch (error) {
    console.error(error);

    return res.status(500).json({
      message: 'Internal server error'
    });
  }
});

// get details for one movie or tv show
router.get('/tmdb/:mediaType/:id', async (req, res) => {
  try {
    const { mediaType, id } = req.params;

    if (mediaType !== 'movie' && mediaType !== 'tv') {
      return res.status(400).json({
        message: 'mediaType must be movie or tv'
      });
    }

    const response = await fetch(
      `${TMDB_BASE_URL}/${mediaType}/${id}`,
      {
        headers: getTmdbHeaders()
      }
    );

    if (!response.ok) {
      return res.status(response.status).json({
        message: 'Media not found'
      });
    }

    const data = await response.json();

    return res.status(200).json(data);

  } catch (error) {
    console.error(error);

    return res.status(500).json({
      message: 'Internal server error'
    });
  }
});

export default router;