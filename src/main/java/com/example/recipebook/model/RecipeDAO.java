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

    public List<Recipe> getRecipe(String type) {
        List<Recipe> recipes = new ArrayList<>();
        String query = "SELECT * FROM recipes WHERE typeOfMeal = ? OR typeOfFood = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, type);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Recipe recipe = new Recipe(
                            resultSet.getString("title"),
                            resultSet.getString("typeOfMeal"),
                            resultSet.getString("typeOfFood"),
                            resultSet.getString("ingredients"),
                            resultSet.getString("instructions")
                    );
                    recipes.add(recipe);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recipes;
    }
}


