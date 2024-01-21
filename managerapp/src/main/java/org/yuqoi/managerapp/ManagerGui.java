package org.yuqoi.managerapp;

import javafx.application.Application;
import javafx.stage.Stage;
import org.yuqoi.managerapp.utils.scenemanager.ScenePaths;
import org.yuqoi.managerapp.utils.scenemanager.SceneSwitcher;

import java.io.IOException;

public class ManagerGui extends Application {

    // Colors: #3498db (Soft Blue)
    // Colors: #2ecc71 (Soft Green)
    // Colors: #ecf0f1 (Light Gray)

    double xOffset;
    double yOffset;
    @Override
    public void start(Stage stage) throws IOException {
        SceneSwitcher.initStage(ScenePaths.MAINPANEL);

    }



    public static void main(String[] args) {
        launch();
    }
}