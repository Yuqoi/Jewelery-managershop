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



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                String name = loginTextPanel.getText();
                String password = passwordTextPanel.getText();

                String hashedPassword = PasswordHasher.passwordHasher(password);


                // TODO: Check if conn is null if yes then we cant run the app
                // TODO: check if the given login name is valid and is in the database if not dont give an error
                try {
                    Connection conn = DatabaseConnector.getConnection();
                    String sql = "SELECT * FROM login WHERE name == ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, name);

                    // execute given query
                    ResultSet rs = ps.executeQuery();

                    // TODO: WE HAVE TO check if the given name isn't null if yes then we cannot get any data
                    // TODO: check if given name is in the database IF NOT then return wrong username/password alert


                } catch (SQLException e) {
                    throw new RuntimeException(e);
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
                System.exit(1);
            }
        });
    }
}
