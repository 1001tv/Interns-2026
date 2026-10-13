import { DataTypes, Model } from 'sequelize';
import sequelize from '../config/database';

class User extends Model {}

User.init(
  {
    id: {
      type: DataTypes.INTEGER,
      autoIncrement: true, // id is generated automatically
      primaryKey: true, // primary key
      allowNull: false // not null
    },

    name: {
      type: DataTypes.STRING,
      allowNull: false // not null
    },

    email: {
      type: DataTypes.STRING,
      allowNull: false, // not null
      unique: true // cannot be repeated
    },

    passwordHash: {
      type: DataTypes.STRING,
      allowNull: false
    },

    age: {
      type: DataTypes.INTEGER,
      allowNull: true 
    },

    favoriteGenres: {
      type: DataTypes.ARRAY(DataTypes.STRING),
      allowNull: true // optional
    },

    securityQuestion: {
      type: DataTypes.STRING,
      allowNull: false // required for password reset
    },

    securityAnswerHash: {
      type: DataTypes.STRING,
      allowNull: false
    }
  },
  {
    sequelize,
    tableName: 'users',
    timestamps: true
  }
);

export default User;