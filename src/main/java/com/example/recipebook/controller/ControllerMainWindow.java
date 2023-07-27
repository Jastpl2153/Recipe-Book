package com.example.recipebook.controller;

import com.example.recipebook.model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ControllerMainWindow {
    @FXML
    void addRecipe(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene("/com/example/recipebook/AddRecipeWindow.fxml", currentStage);
    }

    @FXML
    void menuEnter(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene("/com/example/recipebook/MenuWindow.fxml", currentStage);
    }

    @FXML
    void search(ActionEvent event) {

    }
}
