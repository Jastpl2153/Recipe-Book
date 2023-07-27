package com.example.recipebook.controller;

import com.example.recipebook.model.RecipeDAO;
import com.example.recipebook.model.RecipeDataModel;
import com.example.recipebook.model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerMainWindow {
    private RecipeDAO recipeDAO = new RecipeDAO();

    @FXML
    void addRecipe(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene("/com/example/recipebook/AddRecipeWindow.fxml", currentStage);
    }

    @FXML
    void menuEnter(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene("/com/example/recipebook/MenuWindow.fxml", currentStage);

        ControllerMenuWindow con = (ControllerMenuWindow) SceneSwitcher.getController();
        String type = ((Button) event.getSource()).getText();

        RecipeDataModel.getInstance().setRecipes(recipeDAO.getRecipe(type));

        con.updateRecipeButtons(RecipeDataModel.getInstance().getRecipes());
    }

    @FXML
    void search(ActionEvent event) {

    }
}
