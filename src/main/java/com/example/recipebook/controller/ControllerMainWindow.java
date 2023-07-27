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
        ControllerMenuWindow con = SceneSwitcher.switchSceneMenu("/com/example/recipebook/MenuWindow.fxml", currentStage);

        String type = ((Button) event.getSource()).getText();
        List<Recipe> recipes = recipeDAO.getRecipe(type);

        con.getBox().getChildren().clear();
        for (Recipe recipe : recipes) {
            Button repiceButton = new Button(recipe.getTitle());
            con.getBox().getChildren().add(repiceButton);
        }

        for (Node node :con.getBox().getChildren()) {
            VBox.setMargin(node, new Insets(10, 15, 0, 15));
            Region region = (Region) node;
            region.setMaxWidth(Double.MAX_VALUE);
        }
    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene("/com/example/recipebook/MainWindow.fxml", currentStage);
    }
}
//        try {
//            List<Recipe> recipes = recipeDAO.getRecipe(type);
//            VBox vBox = new VBox();
//            for (Recipe recipe : recipes) {
//                Button repiceButton = new Button(recipe.getTitle());
//                menu.getVbox().getChildren().add(repiceButton);
////                repiceButton.setOnAction(actionEvent -> {});
////                vBox.getChildren().add(repiceButton);
////                menu.setVbox(vBox);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
