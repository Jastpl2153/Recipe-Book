package com.example.recipebook.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    public static void switchScene(String fxmlPath, Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            currentStage.setScene(scene);

            currentStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
