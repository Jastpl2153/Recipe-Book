package com.example.recipebook.controller;

import com.example.recipebook.model.Recipe;
import com.example.recipebook.model.RecipeDataModel;
import com.example.recipebook.model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class ControllerMenuWindow {
    @FXML
    private VBox box;
    private Map<Button, Recipe> buttonRecipeMap = new HashMap<>();
    private Stage stage;

    @FXML
    void back(ActionEvent event) {
        SceneSwitcher.switchScene("/com/example/recipebook/MainWindow.fxml", getStage());
    }

    @FXML
    void sort(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        if (menuItem.getText().equals("А - Я")) {
            sortRecipes(Comparator.comparing(Recipe::getTitle, String.CASE_INSENSITIVE_ORDER));
        } else if (menuItem.getText().equals("Я - А")) {
            sortRecipes(Comparator.comparing(Recipe::getTitle, String.CASE_INSENSITIVE_ORDER).reversed());
        }
    }

    // Обрабатывает события при нажании на кнопку рецепта.
    @FXML
    void handleButtonAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        Recipe recipe = buttonRecipeMap.get(button);
        if (recipe != null) {
            openRecipeWindow(recipe);
        }
    }

    // Обновляет список кнопок рецептов в окне меню на основе переданных рецептов.
    protected void updateRecipeButtons(List<Recipe> recipes) {
        box.getChildren().clear();

        recipes.forEach(recipe -> {
            Button recipeButton = new Button(recipe.getTitle());
            box.getChildren().add(recipeButton);
            buttonRecipeMap.put(recipeButton, recipe);

            recipeButton.setOnAction(this::handleButtonAction);
            VBox.setMargin(recipeButton, new Insets(10, 15, 0, 15));
            recipeButton.setMaxWidth(Double.MAX_VALUE);
            recipeButton.setStyle("-fx-background-color: #b6eed8");
        });
    }

    private void sortRecipes(Comparator<Recipe> comparator) {
        RecipeDataModel.getInstance().getRecipes().sort(comparator);
        updateRecipeButtons(RecipeDataModel.getInstance().getRecipes());
    }

    // Переключает текущую сцену на окно рецепта приложения и загружает данные о рецепте в окно рецепта
    private void openRecipeWindow(Recipe recipe) {
        SceneSwitcher.switchScene("/com/example/recipebook/RecipeWindow.fxml", getStage());
        ControllerRecipeWindow recipeWindow = (ControllerRecipeWindow) SceneSwitcher.getController();

        recipeWindow.getTitle().setText(recipe.getTitle());
        recipeWindow.getIngredients().setText(recipe.getIngredients());
        recipeWindow.getInstructions().setText(recipe.getInstructions());
        recipeWindow.setSelectedRecipe(recipe);
    }

    private Stage getStage() {
        stage = (Stage) box.getScene().getWindow();
        return stage;
    }
}
