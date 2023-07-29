package com.example.recipebook.model;

import org.sqlite.Function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Запросы для БД **/
public class RecipeDAO {
    private Connection connection;

    public RecipeDAO() {
        connection = SQLiteConnection.connection();
        if (connection == null) {
            System.exit(1);
        }
        try {
            Function.create(connection, "lower", new LCase());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для добавления нового рецепта в базу данных
    public void addRecipe(Recipe recipe) {
        String query = "INSERT INTO recipes (title, typeOfMeal, typeOfFood, ingredients, instructions) VALUES (?, ?, ?, ?, ?)";
        executeUpdateQuery(query, recipe.getTitle(), recipe.getTypeOfMeal(), recipe.getTypeOfFood(), recipe.getIngredients(), recipe.getInstructions());
    }

    // Метод для получения списка рецептов из базы данных по указанному типу
    public List<Recipe> getRecipe(String type) {
        String query = "SELECT * FROM recipes WHERE typeOfMeal = ? OR typeOfFood = ?";
        return executeQuery(query, type, type);
    }

    // Метод для удаления рецепта из базы данных
    public void deleteRecipe(Recipe recipe) {
        String query = "DELETE FROM recipes WHERE title = ? AND typeOfMeal = ? AND typeOfFood = ?";
        executeUpdateQuery(query, recipe.getTitle(), recipe.getTypeOfMeal(), recipe.getTypeOfFood());
    }

    // Метод для обновления рецепта в базе данных
    public void updateRecipe(Recipe recipe) {
        String query = "UPDATE recipes SET title = ?, typeOfMeal = ?, typeOfFood = ?, ingredients = ?, instructions = ? WHERE id = ?";
        executeUpdateQuery(query, recipe.getTitle(), recipe.getTypeOfMeal(), recipe.getTypeOfFood(), recipe.getIngredients(), recipe.getInstructions(), recipe.getId());
    }

    // Метод для поиска рецептов по заданному слову в базе данных
    public List<Recipe> searchRecipes(String searchWord) {
        String query = "SELECT DISTINCT * FROM recipes WHERE " +
                "lower(title) LIKE ? OR " +
                "lower(typeOfMeal) LIKE ? OR " +
                "lower(typeOfMeal) LIKE ? OR " +
                "lower(ingredients) LIKE ?";
        String searchPattern = "%" + searchWord + "%";
        return executeQuery(query, searchPattern, searchPattern, searchPattern, searchPattern);
    }

    // Общий метод для выполнения запроса без возвращаемого результата
    private void executeUpdateQuery(String query, Object... parameters) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Общий метод для выполнения запроса с возвращаемым результатом
    private List<Recipe> executeQuery(String query, Object... parameters) {
        List<Recipe> recipes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Recipe recipe = new Recipe(
                            resultSet.getString("title"),
                            resultSet.getString("typeOfMeal"),
                            resultSet.getString("typeOfFood"),
                            resultSet.getString("ingredients"),
                            resultSet.getString("instructions"),
                            resultSet.getInt("id")
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


