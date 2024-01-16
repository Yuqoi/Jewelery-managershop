package org.yuqoi.managerapp;

import javafx.application.Application;
import javafx.stage.Stage;
import org.yuqoi.managerapp.models.MechanismType;
import org.yuqoi.managerapp.models.Sex;
import org.yuqoi.managerapp.models.Watch;
import org.yuqoi.managerapp.utils.ScenePaths;
import org.yuqoi.managerapp.utils.SceneSwitcher;

import java.io.IOException;

public class ManagerGui extends Application {

    // Colors: #3498db (Soft Blue)
    // Colors: #2ecc71 (Soft Green)
    // Colors: #ecf0f1 (Light Gray)

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