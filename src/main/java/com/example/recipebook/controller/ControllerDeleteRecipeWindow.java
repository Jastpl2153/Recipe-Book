package com.example.recipebook.controller;

import com.example.recipebook.model.Recipe;
import com.example.recipebook.model.RecipeDAO;
import com.example.recipebook.model.RecipeDataModel;
import com.example.recipebook.model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControllerDeleteRecipeWindow {
    private RecipeDAO recipeDAO = new RecipeDAO();

    private Recipe recipeToDelete;
    private Stage stage;

    public void setRecipe(Recipe recipe, Stage stage) {
        this.recipeToDelete = recipe;
        this.stage = stage;
    }

    @FXML
    void isDelete(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (button.getText().equals("Да") && recipeToDelete != null) {
            try {
               recipeDAO.deleteRecipe(recipeToDelete);
               RecipeDataModel.getInstance().getRecipes().remove(recipeToDelete);

               SceneSwitcher.switchScene("/com/example/recipebook/MenuWindow.fxml", stage);

               ControllerMenuWindow window = (ControllerMenuWindow) SceneSwitcher.getController();
               window.updateRecipeButtons(RecipeDataModel.getInstance().getRecipes());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        exit(button);
    }

    private void exit(Button button){
        Scene scene = button.getScene();
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }
}
