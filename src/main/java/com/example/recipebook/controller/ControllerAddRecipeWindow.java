package com.example.recipebook.controller;

import com.example.recipebook.model.Recipe;
import com.example.recipebook.model.RecipeDAO;
import com.example.recipebook.model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControllerAddRecipeWindow{
    @FXML
    private TextField nameEat;

    @FXML
    private SplitMenuButton typeOfFood;

    @FXML
    private SplitMenuButton typeOfMeal;

    @FXML
    private TextArea ingredients;

    @FXML
    private TextArea instructions;

    private RecipeDAO recipeDAO = new RecipeDAO();
    private Recipe recipe;

    @FXML
    void EnterTypeOfFood(ActionEvent event) {
        typeOfFood.setText(((MenuItem) event.getSource()).getText());
    }

    @FXML
    void EnterTypeOfMeal(ActionEvent event) {
        typeOfMeal.setText(((MenuItem) event.getSource()).getText());
    }

    @FXML
    void addRecipe(ActionEvent event) {
        if (!nameEat.getText().isEmpty() && !typeOfFood.getText().isEmpty() && !typeOfMeal.getText().isEmpty()
                && !ingredients.getText().isEmpty() && !instructions.getText().isEmpty()){
            try {
                recipe = new Recipe(nameEat.getText(), typeOfFood.getText(), typeOfMeal.getText(), ingredients.getText(), instructions.getText());
                recipeDAO.addRecipe(recipe);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            cleanField();
            //TODO: Реализовать переход к странице рецепта.
        }
    }

    private void cleanField(){
        nameEat.setText("");
        typeOfFood.setText("");
        typeOfMeal.setText("");
        ingredients.setText("");
        instructions.setText("");
    }

    @FXML
    void back(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene("/com/example/recipebook/MainWindow.fxml", currentStage);
    }
}
