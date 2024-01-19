package org.yuqoi.managerapp.utils;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.yuqoi.managerapp.ManagerGui;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class SceneSwitcher {

    private static Stage primaryStage;
    private static double xOffset;
    private static double yOffset;

    private static double xPopupset;
    private static double yPopupset;
    // getters
    public static Stage getPrimaryStage() {
        return primaryStage;
    }


    // initializes stage and sets fxml file into scene creating a new window
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


    // makes a popup fxml panel (for add panels and more)
    public static void makePopup(ScenePaths fxmlPopupPath){
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(ManagerGui.class.getResource(fxmlPopupPath.getFxmlFileName())));
            Scene popupScene = new Scene(parent);
            Stage popupStage = new Stage();

            popupStage.setScene(popupScene);
            popupStage.setResizable(false);
            popupStage.initStyle(StageStyle.UNDECORATED);
            popupScene.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xPopupset = event.getSceneX();
                    yPopupset = event.getSceneY();
                }
            });
            popupScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    popupStage.setX(event.getScreenX() - xPopupset);
                    popupStage.setY(event.getScreenY() - yPopupset);
                }
            });
            popupStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // method for changing scenes inside mainpanel
    public static void changeScene(Pane viewPanel, ScenePaths fxmlPath){
        try {
            Pane pane = FXMLLoader.load(Objects.requireNonNull(ManagerGui.class.getResource(fxmlPath.getFxmlFileName())));
            viewPanel.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
