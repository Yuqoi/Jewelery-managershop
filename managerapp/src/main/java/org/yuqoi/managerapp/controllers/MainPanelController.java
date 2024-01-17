package org.yuqoi.managerapp.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.yuqoi.managerapp.ManagerGui;
import org.yuqoi.managerapp.utils.ScenePaths;

import java.io.IOException;
import java.util.Objects;

public class MainPanelController {


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

    public void btnInventory(MouseEvent mouseEvent) {
        try {
            Pane pane = FXMLLoader.load(Objects.requireNonNull(ManagerGui.class.getResource(ScenePaths.INVENTORYPANEL.getFxmlFileName())));
            viewPanel.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSales(MouseEvent mouseEvent) {
        try {
            Pane pane = FXMLLoader.load(Objects.requireNonNull(ManagerGui.class.getResource(ScenePaths.SALESPANEL.getFxmlFileName())));
            viewPanel.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnInvoice(MouseEvent mouseEvent) {
        try {
            Pane pane = FXMLLoader.load(Objects.requireNonNull(ManagerGui.class.getResource(ScenePaths.INVOICEPANEL.getFxmlFileName())));
            viewPanel.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSettings(MouseEvent mouseEvent) {
        try {
            Pane pane = FXMLLoader.load(Objects.requireNonNull(ManagerGui.class.getResource(ScenePaths.SETTINGSPANEL.getFxmlFileName())));
            viewPanel.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}
