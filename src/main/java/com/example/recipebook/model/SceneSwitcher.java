package com.example.recipebook.model;

import com.example.recipebook.controller.ControllerMenuWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    private static FXMLLoader loader;

    public static void switchScene(String fxmlPath, Stage currentStage) {
        try {
            loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ControllerMenuWindow getController(){
        return loader.getController();
    }
}
