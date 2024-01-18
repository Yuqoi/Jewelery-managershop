package org.yuqoi.managerapp.controllers.windows;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.yuqoi.managerapp.ManagerGui;
import org.yuqoi.managerapp.utils.ScenePaths;
import org.yuqoi.managerapp.utils.SceneSwitcher;

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





}
