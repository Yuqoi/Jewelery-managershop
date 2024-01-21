package org.yuqoi.managerapp;

import javafx.application.Application;
import javafx.stage.Stage;
import org.yuqoi.managerapp.utils.scenemanager.ScenePaths;
import org.yuqoi.managerapp.utils.scenemanager.SceneSwitcher;

import java.io.IOException;

public class ManagerGui extends Application {

    double xOffset;
    double yOffset;
    @Override
    public void start(Stage stage) throws IOException {
        SceneSwitcher.initStage(ScenePaths.LOGINPANEL);
    }
    public static void main(String[] args) {
        launch();
    }
}