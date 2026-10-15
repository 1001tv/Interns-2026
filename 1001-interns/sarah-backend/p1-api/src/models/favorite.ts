import { DataTypes, Model } from 'sequelize';
import sequelize from '../config/database';

class Favorite extends Model {}

Favorite.init(
  {
    id: {
      type: DataTypes.INTEGER,
      autoIncrement: true,
      primaryKey: true,
      allowNull: false
    },

    userId: {
      type: DataTypes.INTEGER,
      allowNull: false
    },

    tmdbId: {
      type: DataTypes.INTEGER,
      allowNull: false
    },

    mediaType: {
      type: DataTypes.STRING,
      allowNull: false
    }
  },
  {
    sequelize,
    tableName: 'favorites',
    timestamps: true
  }
);

export default Favorite;
