package com.example.recipebook.model;

import java.sql.*;

public class RecipeDAO {
    private Connection connection;

    public RecipeDAO() {
        connection = SQLiteConnection.connection();
        if (connection == null){
            System.exit(1);
        }
    }

    public boolean isDbConnection(){
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addRecipe(Recipe recipe) throws SQLException {
        String query = "INSERT INTO recipes (title, typeOfMeal, typeOfFood, ingredients, instructions) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, recipe.getTitle());
            preparedStatement.setString(2, recipe.getTypeOfMeal());
            preparedStatement.setString(3, recipe.getTypeOfFood());
            preparedStatement.setString(4, recipe.getIngredients());
            preparedStatement.setString(5, recipe.getInstructions());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}


