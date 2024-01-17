package org.yuqoi.managerapp.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.yuqoi.managerapp.ManagerGui;
import org.yuqoi.managerapp.utils.ScenePaths;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPanelController implements Initializable {


    // all buttons in main panel
    public Button inventoryBtn;
    public Button manageBtn;
    public Button salesBtn;
    public Button invoiceBtn;
    public Button settingsBtn;

    // main panel to change views
    public Pane viewPanel;

    // Close window and minimize window functions
    public void closeWindow(ActionEvent event) {
        Platform.exit();
    }
    public void minimizeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inventoryBtn.setOnMouseClicked(event -> {
            try {
                Pane pane = FXMLLoader.load(Objects.requireNonNull(ManagerGui.class.getResource("views/inventory-panel.fxml")));
                viewPanel.getChildren().setAll(pane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void btnSettings(ActionEvent event) {
    }

    public void btnInvoice(ActionEvent event) {
    }

    public void btnSales(ActionEvent event) {
    }

    public void btnInventory(ActionEvent event) {
    }
}
