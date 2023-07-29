package com.example.recipebook.controller;

import com.example.recipebook.model.Recipe;
import com.example.recipebook.model.RecipeDAO;
import com.example.recipebook.model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControllerUpdateRecipeWindow {
    @FXML
    private TextArea ingredients;

    @FXML
    private TextArea instructions;

    @FXML
    private TextField nameEat;

    @FXML
    private SplitMenuButton typeOfFood;

    @FXML
    private SplitMenuButton typeOfMeal;

    private Recipe updateRecipe;
    private RecipeDAO recipeDAO = new RecipeDAO();

    @FXML
    void back(ActionEvent event) {
        openRecipeWindow(updateRecipe);
    }

    @FXML
    void update(ActionEvent event) {
        updateRecipeData();

        try {
            recipeDAO.updateRecipe(updateRecipe);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        openRecipeWindow(updateRecipe);
    }

    public void setUpdateRecipe(Recipe updateRecipe) {
        this.updateRecipe = updateRecipe;
        populateFields();
    }

    private void populateFields() {
        if (updateRecipe != null) {
            nameEat.setText(updateRecipe.getTitle());
            ingredients.setText(updateRecipe.getIngredients());
            instructions.setText(updateRecipe.getInstructions());
            typeOfMeal.setText(updateRecipe.getTypeOfMeal());
            typeOfFood.setText(updateRecipe.getTypeOfFood());
        }
    }

    private void updateRecipeData() {
        updateRecipe.setTitle(nameEat.getText());
        updateRecipe.setIngredients(ingredients.getText());
        updateRecipe.setInstructions(instructions.getText());
        updateRecipe.setTypeOfMeal(typeOfMeal.getText());
        updateRecipe.setTypeOfFood(typeOfFood.getText());
    }

    private void openRecipeWindow(Recipe recipe) {
        Stage currentStage = (Stage) nameEat.getScene().getWindow();
        SceneSwitcher.switchScene("/com/example/recipebook/RecipeWindow.fxml",currentStage);

        ControllerRecipeWindow recipeWindow = (ControllerRecipeWindow) SceneSwitcher.getController();
        recipeWindow.getTitle().setText(recipe.getTitle());
        recipeWindow.getIngredients().setText(recipe.getIngredients());
        recipeWindow.getInstructions().setText(recipe.getInstructions());
        recipeWindow.setSelectedRecipe(recipe);
    }

    @FXML
    void UpdateTypeOfFood(ActionEvent event) {
        typeOfFood.setText(((MenuItem) event.getSource()).getText());
    }

    @FXML
    void UpdateTypeOfMeal(ActionEvent event) {
        typeOfMeal.setText(((MenuItem) event.getSource()).getText());
    }
}
