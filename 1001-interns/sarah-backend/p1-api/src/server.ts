import app from './app';
import sequelize from './config/database';

const port = Number(process.env.PORT || 3000);

async function startServer() {
  try {
    await sequelize.authenticate();
    console.log('Database connected successfully');

    app.listen(port, () => {
      console.log(`Server is running on http://localhost:${port}`);
    });
  } catch (error) {
    console.error('Unable to connect to the database:', error);
  }
}

startServer();