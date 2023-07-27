package com.example.recipebook.controller;

import com.example.recipebook.model.RecipeDataModel;
import com.example.recipebook.model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerRecipeWindow {

    @FXML
    private Label ingredients;

    @FXML
    private Label instructions;

    @FXML
    private Label title;

    @FXML
    void back(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene("/com/example/recipebook/MenuWindow.fxml", currentStage);

        ControllerMenuWindow controllerMenuWindow = (ControllerMenuWindow) SceneSwitcher.getController();
        controllerMenuWindow.updateRecipeButtons(RecipeDataModel.getInstance().getRecipes());
    }

    public Label getIngredients() {
        return ingredients;
    }

    public Label getInstructions() {
        return instructions;
    }

    public Label getTitle() {
        return title;
    }
}
