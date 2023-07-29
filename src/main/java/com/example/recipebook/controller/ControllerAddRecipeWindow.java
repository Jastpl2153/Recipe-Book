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

public class ControllerAddRecipeWindow {
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
    private Stage stage;

    @FXML
    void EnterTypeOfFood(ActionEvent event) {
        setMenuButtonText(typeOfFood, event);
    }

    @FXML
    void EnterTypeOfMeal(ActionEvent event) {
        setMenuButtonText(typeOfMeal, event);
    }

    @FXML
    void addRecipe(ActionEvent event) {
        if (areFieldsValid()) {
            Recipe recipe = createRecipeFromFields();
            recipeDAO.addRecipe(recipe);
            openRecipeWindow(recipe);
        }
    }

    private boolean areFieldsValid() {
        return !nameEat.getText().isEmpty() &&
                !typeOfFood.getText().isEmpty() &&
                !typeOfMeal.getText().isEmpty() &&
                !ingredients.getText().isEmpty() &&
                !instructions.getText().isEmpty();
    }

    private Recipe createRecipeFromFields() {
        return new Recipe(
                nameEat.getText(),
                typeOfMeal.getText(),
                typeOfFood.getText(),
                ingredients.getText(),
                instructions.getText()
        );
    }

    private void openRecipeWindow(Recipe recipe) {
        SceneSwitcher.switchScene("/com/example/recipebook/RecipeWindow.fxml", getStage());
        ControllerRecipeWindow recipeWindow = (ControllerRecipeWindow) SceneSwitcher.getController();

        recipeWindow.getTitle().setText(recipe.getTitle());
        recipeWindow.getIngredients().setText(recipe.getIngredients());
        recipeWindow.getInstructions().setText(recipe.getInstructions());
        recipeWindow.setSelectedRecipe(recipe);
        recipeWindow.setOpenedFromAddRecipeWindow(true);
    }

    @FXML
    void back(ActionEvent event) {
        SceneSwitcher.switchScene("/com/example/recipebook/MainWindow.fxml", getStage());
    }

    private void setMenuButtonText(SplitMenuButton button, ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        button.setText(menuItem.getText());
    }

    private Stage getStage() {
        stage = (Stage) nameEat.getScene().getWindow();
        return stage;
    }
}
