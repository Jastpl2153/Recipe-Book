package com.example.recipebook.controller;

import com.example.recipebook.model.Recipe;
import com.example.recipebook.model.RecipeDAO;
import com.example.recipebook.model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class ControllerMainWindow {
    private RecipeDAO recipeDAO = new RecipeDAO();

    @FXML
    void addRecipe(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene("/com/example/recipebook/AddRecipeWindow.fxml", currentStage);
    }

    @FXML
    void menuEnter(ActionEvent event) throws SQLException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene("/com/example/recipebook/MenuWindow.fxml", currentStage);

        ControllerMenuWindow con = SceneSwitcher.getController();
        String type = ((Button) event.getSource()).getText();
        List<Recipe> recipes = recipeDAO.getRecipe(type);

        updateRecipeButtons(con, recipes);
    }

    private void updateRecipeButtons(ControllerMenuWindow controller, List<Recipe> recipes) {
        controller.getBox().getChildren().clear();

        controller.getBox().getChildren().addAll(recipes.stream()
                .map(recipe -> new Button(recipe.getTitle()))
                .toList());

        controller.getBox().getChildren().forEach(node -> {
            VBox.setMargin(node, new Insets(10, 15, 0, 15));
            ((Region) node).setMaxWidth(Double.MAX_VALUE);
            node.setStyle("-fx-background-color: #b6eed8");
        });
    }

    @FXML
    void search(ActionEvent event) {

    }
}
