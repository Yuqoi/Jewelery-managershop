package org.yuqoi.managerapp.controllers.windows;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.yuqoi.managerapp.ManagerGui;
import org.yuqoi.managerapp.utils.ScenePaths;
import org.yuqoi.managerapp.utils.SceneSwitcher;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPanelController {



    public Pane viewPanel;

    private Button previousClickedButton;


    // Close window and minimize window functions
    public void closeWindow(ActionEvent event) {
        Platform.exit();
    }
    public void minimizeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }



    public void btnInventory(MouseEvent mouseEvent) {
        SceneSwitcher.changeScene(viewPanel, ScenePaths.INVENTORYPANEL);
    }

    public void btnSales(MouseEvent mouseEvent) {
        SceneSwitcher.changeScene(viewPanel, ScenePaths.SALESPANEL);
    }

    public void btnInvoice(MouseEvent mouseEvent) {
        SceneSwitcher.changeScene(viewPanel, ScenePaths.INVOICEPANEL);
    }

    public void btnSettings(MouseEvent mouseEvent) {
        SceneSwitcher.changeScene(viewPanel, ScenePaths.SETTINGSPANEL);
    }



    public void changeColors(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        // Change the color of the clicked button

        clickedButton.setStyle("-fx-background-color: red;");

        // Revert the color of the previously clicked button (if any)
        if (previousClickedButton != null && previousClickedButton != clickedButton) {
            previousClickedButton.setStyle("-fx-background-color: white;");
        }

        // Update the reference to the previously clicked button
        previousClickedButton = clickedButton;
    }
}
