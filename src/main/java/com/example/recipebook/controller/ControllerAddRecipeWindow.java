package com.example.recipebook.controller;

import com.example.recipebook.model.Recipe;
import com.example.recipebook.model.RecipeDAO;
import com.example.recipebook.model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerAddRecipeWindow implements Initializable {
    @FXML
    private TextField nameEat;

    @FXML
    private TextField typeOfFood;

    @FXML
    private TextField typeOfMeal;

    @FXML
    private TextArea ingredients;

    @FXML
    private TextArea instructions;

    private RecipeDAO recipeDAO = new RecipeDAO();
    private Recipe recipe;
    @FXML
    void addRecipe(ActionEvent event) {
        if (!nameEat.getText().isEmpty()
                && !typeOfFood.getText().isEmpty()
                && !typeOfMeal.getText().isEmpty()
                && !ingredients.getText().isEmpty()
                && !instructions.getText().isEmpty()){
            recipe = new Recipe(nameEat.getText(), typeOfFood.getText(), typeOfMeal.getText(),
                    ingredients.getText(), instructions.getText());
            try {
                recipeDAO.addRecipe(recipe);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            cleanField();
        }
    }

    private void cleanField(){
        nameEat.setText("");
        typeOfFood.setText("");
        typeOfMeal.setText("");
        ingredients.setText("");
        instructions.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (recipeDAO.isDbConnection()){
            System.out.println("+");
        }
    }

    @FXML
    void back(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene("/com/example/recipebook/MainWindow.fxml", currentStage);
    }
}
