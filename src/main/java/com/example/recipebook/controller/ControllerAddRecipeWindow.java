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

public class ControllerAddRecipeWindow{
    @FXML
    private TextField nameEat;

    @FXML
    private SplitMenuButton typeOfFood;

    @FXML
    private SplitMenuButton typeOfMeal;

    @FXML
    private TextArea ingredients;

    @FXML
    private TextArea instructions;

    private RecipeDAO recipeDAO = new RecipeDAO();
    private Recipe recipe;
    private Stage stage;

    @FXML
    void EnterTypeOfFood(ActionEvent event) {
        typeOfFood.setText(((MenuItem) event.getSource()).getText());
    }

    @FXML
    void EnterTypeOfMeal(ActionEvent event) {
        typeOfMeal.setText(((MenuItem) event.getSource()).getText());
    }

    @FXML
    void addRecipe(ActionEvent event) {
        if (!nameEat.getText().isEmpty() && !typeOfFood.getText().isEmpty() && !typeOfMeal.getText().isEmpty()
                && !ingredients.getText().isEmpty() && !instructions.getText().isEmpty()){
            try {
                recipe = new Recipe(nameEat.getText(), typeOfMeal.getText(), typeOfFood.getText(), ingredients.getText(), instructions.getText());
                recipeDAO.addRecipe(recipe);
                openRecipeWindow();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void openRecipeWindow(){
        SceneSwitcher.switchScene("/com/example/recipebook/RecipeWindow.fxml", getStage());
        ControllerRecipeWindow recipeWindow = (ControllerRecipeWindow) SceneSwitcher.getController();

        if (recipe != null) {
            recipeWindow.getTitle().setText(recipe.getTitle());
            recipeWindow.getIngredients().setText(recipe.getIngredients());
            recipeWindow.getInstructions().setText(recipe.getInstructions());
            recipeWindow.setSelectedRecipe(recipe);
            recipeWindow.setOpenedFromAddRecipeWindow(true);
        }
    }

    @FXML
    void back(ActionEvent event) {
        SceneSwitcher.switchScene("/com/example/recipebook/MainWindow.fxml", getStage());
    }

    private Stage getStage(){
        stage = (Stage) nameEat.getScene().getWindow();
        return stage;
    }
}
