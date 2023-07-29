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

    private void handleButtonAction(Button button) {
        SceneSwitcher.switchScene("/com/example/recipebook/RecipeWindow.fxml", getStage());

        ControllerRecipeWindow recipeWindow = (ControllerRecipeWindow) SceneSwitcher.getController();

        Recipe recipe = buttonRecipeMap.get(button);
        if (recipe != null) {
            recipeWindow.getTitle().setText(recipe.getTitle());
            recipeWindow.getIngredients().setText(recipe.getIngredients());
            recipeWindow.getInstructions().setText(recipe.getInstructions());
            recipeWindow.setSelectedRecipe(recipe);
        }
    }

    protected void updateRecipeButtons(List<Recipe> recipes) {
        box.getChildren().clear();

        recipes.forEach(recipe -> {
            Button recipeButton = new Button(recipe.getTitle());
            box.getChildren().add(recipeButton);
            buttonRecipeMap.put(recipeButton, recipe);

            recipeButton.setOnAction(event -> handleButtonAction(recipeButton));
            VBox.setMargin(recipeButton, new Insets(10, 15, 0, 15));
            recipeButton.setMaxWidth(Double.MAX_VALUE);
            recipeButton.setStyle("-fx-background-color: #b6eed8");
        });
    }

    @FXML
    void sort(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        if (menuItem.getText().equals("А - Я")) {
            sortRecipes((recipe1, recipe2) -> recipe1.getTitle().compareToIgnoreCase(recipe2.getTitle()));
        } else if (menuItem.getText().equals("Я - А")) {
            sortRecipes((recipe1, recipe2) -> recipe2.getTitle().compareToIgnoreCase(recipe1.getTitle()));
        }
    }

    private void sortRecipes(Comparator<Recipe> comparator) {
        RecipeDataModel.getInstance().getRecipes().sort(comparator);
        updateRecipeButtons(RecipeDataModel.getInstance().getRecipes());
    }

    private Stage getStage(){
        stage = (Stage) box.getScene().getWindow();
        return stage;
    }
}
