package org.yuqoi.managerapp.controllers.panels;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPanelController implements Initializable {
    public TextField addPanelName;
    public TextField addPanelBrand;
    public ComboBox addPanelGenderBox;
    public TextField addPanelMPN;
    public ComboBox addPanelMechanismBox;
    public TextField addPanelPrice;


    // buttons
    public Button closeWindowBtn;
    public Button applyDataBtn;




    public void applyData(MouseEvent mouseEvent) {
        // TODO we have to apply data to database, check if there isnt any null textfield and if it's correct
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeWindowBtn.setOnMouseClicked(event -> {
            ((Stage) closeWindowBtn.getScene().getWindow()).close();
        });

    }
}
