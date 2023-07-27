package com.example.recipebook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Recipe> getRecipe(String type) throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        String query = "SELECT * FROM recipes WHERE typeOfMeal = ? OR typeOfFood = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, type);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Recipe recipe = new Recipe();
                    recipe.setTitle(resultSet.getString("title"));
                    recipe.setTypeOfMeal(resultSet.getString("typeOfMeal"));
                    recipe.setTypeOfFood(resultSet.getString("typeOfFood"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setInstructions(resultSet.getString("instructions"));
                    recipes.add(recipe);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return recipes;
    }
}


