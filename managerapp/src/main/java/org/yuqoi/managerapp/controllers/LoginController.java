package org.yuqoi.managerapp.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.yuqoi.managerapp.utils.DatabaseConnector;
import org.yuqoi.managerapp.utils.PasswordHasher;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class LoginController implements Initializable {


    // BUTTONS
    public Button exitBtn;
    public Button minBtn;
    public Button loginBtn;

    // TEXTFIELDS
    public TextField loginTextPanel;
    public PasswordField passwordTextPanel;
    public Label warningText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                String name = loginTextPanel.getText();
                String password = passwordTextPanel.getText();

                String hashedPassword = PasswordHasher.passwordHasher(password);


                // TODO: check if the given login name is valid and is in the database if not dont give an error
                try {
                    Connection conn = DatabaseConnector.getConnection();
                    String sql = "SELECT * FROM login WHERE name = ?";
                    assert conn != null;
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setString(1, name);
                        // execute given query
                        ResultSet rs = ps.executeQuery();


                        while (rs.next()) {
                            String login = rs.getString("name");
                            String loginPassword = rs.getString("password");

                            if (login.equals(name) && loginPassword.equals(hashedPassword)) {
                                warningText.setText("-Login Approved-");

                            }

                        }

                        rs.close();
                        ps.close();
                    }


                } catch (SQLException e) {
                    warningText.setText("-Connection is NULL-");
//                    throw new RuntimeException(e);
                }

            }
        });
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
                Stage stage = (Stage) exitBtn.getScene().getWindow();
                stage.close();
            }
        });
    }

    // check if the given namne and password is correct
    public void validateLogin(){

    }
}
