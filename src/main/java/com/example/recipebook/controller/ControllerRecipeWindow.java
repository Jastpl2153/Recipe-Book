package com.example.recipebook.controller;

import com.example.recipebook.model.Recipe;
import com.example.recipebook.model.RecipeDataModel;
import com.example.recipebook.model.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class ControllerRecipeWindow {

    @Getter
    @FXML
    private Label ingredients;

    @Getter
    @FXML
    private Label instructions;

    @Getter
    @FXML
    private Label title;

    @Setter
    private Recipe selectedRecipe;
    private boolean openedFromAddRecipeWindow = false;

    @FXML
    void back(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        handleBackAction(currentStage);
    }

    // Возвращает пользователя на предыдущее окно в зависимости от того, откуда было открыто окно рецепта.
    // Если окно открыто из окна добавления рецепта, переходит на главное окно (MainWindow.fxml).
    // Иначе переходит на окно меню рецептов (MenuWindow.fxml) и обновляет кнопки рецептов.

    private void handleBackAction(Stage currentStage) {
        if (openedFromAddRecipeWindow) {
            SceneSwitcher.switchScene("/com/example/recipebook/MainWindow.fxml", currentStage);
        } else {
            SceneSwitcher.switchScene("/com/example/recipebook/MenuWindow.fxml", currentStage);

            ControllerMenuWindow controllerMenuWindow = (ControllerMenuWindow) SceneSwitcher.getController();
            controllerMenuWindow.updateRecipeButtons(RecipeDataModel.getInstance().getRecipes());
        }
    }

    @FXML
    void delete(ActionEvent event) {
        handleDeleteAction(event);
    }

    private void handleDeleteAction(ActionEvent event) {
        SceneSwitcher.switchScene("/com/example/recipebook/DeleteRecipeWindow.fxml", new Stage());
        ControllerDeleteRecipeWindow deleteController = (ControllerDeleteRecipeWindow) SceneSwitcher.getController();
        deleteController.setRecipe(selectedRecipe, (Stage) ((Button) event.getSource()).getScene().getWindow());
    }


    // Переходит на окно редактирования рецепта.
    @FXML
    void updateRecipe(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene("/com/example/recipebook/UpdateRecipeWindow.fxml", currentStage);

        handleUpdateAction(selectedRecipe);
    }

    private void handleUpdateAction(Recipe selectedRecipe) {
        ControllerUpdateRecipeWindow window = (ControllerUpdateRecipeWindow) SceneSwitcher.getController();
        window.setUpdateRecipe(selectedRecipe);
    }

    // Устанавливает флаг, указывающий, было ли окно открыто из окна добавления рецепта.
    // Это позволяет определить, на какое окно перейти при нажатии кнопки "Назад".
    public void setOpenedFromAddRecipeWindow(boolean openedFromAddRecipeWindow) {
        this.openedFromAddRecipeWindow = openedFromAddRecipeWindow;
    }
}
