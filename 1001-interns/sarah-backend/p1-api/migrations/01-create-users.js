'use strict';

module.exports = {
  async up(queryInterface, Sequelize) {
    await queryInterface.createTable('users', {
      id: {
        type: Sequelize.INTEGER,
        autoIncrement: true,
        primaryKey: true,
        allowNull: false
      },

      name: {
        type: Sequelize.STRING,
        allowNull: false
      },

      email: {
        type: Sequelize.STRING,
        allowNull: false,
        unique: true
      },

      passwordHash: {
        type: Sequelize.STRING,
        allowNull: false
      },

      age: {
        type: Sequelize.INTEGER,
        allowNull: true
      },

      favoriteGenres: {
        type: Sequelize.ARRAY(Sequelize.STRING),
        allowNull: true
      },

      securityQuestion: {
        type: Sequelize.STRING,
        allowNull: false
      },

      securityAnswerHash: {
        type: Sequelize.STRING,
        allowNull: false
      },

      createdAt: {
        type: Sequelize.DATE,
        allowNull: false
      },

      updatedAt: {
        type: Sequelize.DATE,
        allowNull: false
      }
    });
  },

  async down(queryInterface, Sequelize) {
    await queryInterface.dropTable('users');
  }
};
