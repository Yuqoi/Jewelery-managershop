package org.yuqoi.managerapp.utils;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.yuqoi.managerapp.ManagerGui;

import java.io.IOException;
import java.net.URL;

public class SceneSwitcher {

    private static Stage primaryStage;
    private static double xOffset;
    private static double yOffset;

    public static void initStage(ScenePaths fxmlPath){
        try {
            URL url = ManagerGui.class.getResource(fxmlPath.getFxmlFileName());
            Parent root = FXMLLoader.load(url);

            Scene scene = new Scene(root);

            primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            scene.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setX(event.getScreenX() - xOffset);
                    primaryStage.setY(event.getScreenY() - yOffset);
                }
            });
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
