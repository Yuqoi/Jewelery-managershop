package org.yuqoi.managerapp.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    // BUTTONS
    public Button exitBtn;
    public Button minBtn;
    public Button loginBtn;

    // TEXTFIELDS
    public TextField loginTextPanel;
    public PasswordField passwordTextPanel;



    public void loginUser(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        minBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) minBtn.getScene().getWindow();
                stage.setIconified(true);
            }
        });

        exitBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(1);
            }
        });
    }
}
