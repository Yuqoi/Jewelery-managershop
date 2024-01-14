package org.yuqoi.managerapp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.yuqoi.managerapp.utils.DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ManagerGui extends Application {

    // Colors: #3498db (Soft Blue)
    // Colors: #2ecc71 (Soft Green)
    // Colors: #ecf0f1 (Light Gray)

    double xOffset;
    double yOffset;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ManagerGui.class.getResource("views/login-panel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

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
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });


        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {

        launch();
    }
}