package com.example.recipebook.controller;

import com.example.recipebook.model.Recipe;
import com.example.recipebook.model.RecipeDAO;
import com.example.recipebook.model.RecipeDataModel;
import com.example.recipebook.model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class ControllerMainWindow {
    @FXML
    private TextField search;
    private RecipeDAO recipeDAO = new RecipeDAO();
    private Stage stage;

    // Переключает сцену на окно добавления рецепта.
    @FXML
    void addRecipe(ActionEvent event) {
        SceneSwitcher.switchScene("/com/example/recipebook/AddRecipeWindow.fxml", getStage());
    }

    // Переключает сцену на окно с отображением рецептов выбранного типа блюда.
    @FXML
    void menuEnter(ActionEvent event) {
        String type = ((Button) event.getSource()).getText();
        List<Recipe> recipes = recipeDAO.getRecipe(type);

        RecipeDataModel.getInstance().setRecipes(recipes);

        SceneSwitcher.switchScene("/com/example/recipebook/MenuWindow.fxml", getStage());
        updateMenuRecipeButtons();
    }

    // Выполняет поиск рецептов по введенному пользователем слову.
    @FXML
    void search(ActionEvent event) {
        String userInput = search.getText();
        List<Recipe> recipes = getRecipesBySearch(userInput);

        RecipeDataModel.getInstance().setRecipes(recipes);

        SceneSwitcher.switchScene("/com/example/recipebook/MenuWindow.fxml", getStage());
        updateMenuRecipeButtons();
    }

    private void updateMenuRecipeButtons() {
        ControllerMenuWindow con = (ControllerMenuWindow) SceneSwitcher.getController();
        con.updateRecipeButtons(RecipeDataModel.getInstance().getRecipes());
    }

    private List<Recipe> getRecipesBySearch(String userInput) {
        return recipeDAO.searchRecipes(userInput.toLowerCase());
    }

    private Stage getStage() {
        stage = (Stage) search.getScene().getWindow();
        return stage;
    }
}
