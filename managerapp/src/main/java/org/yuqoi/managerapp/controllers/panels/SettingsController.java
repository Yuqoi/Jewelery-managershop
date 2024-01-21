package org.yuqoi.managerapp.controllers.panels;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.yuqoi.managerapp.controllers.windows.LoginController;
import org.yuqoi.managerapp.utils.DatabaseConnector;
import org.yuqoi.managerapp.utils.PasswordHasher;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    public PasswordField secondPassword;
    public PasswordField firstPassword;
    public Label warningText;
    public Label loggedText;

    public void applyChanges(MouseEvent mouseEvent) throws SQLException {

        String firstPasswordText = firstPassword.getText();
        String secondPasswordText = secondPassword.getText();
        if (firstPasswordText.equals(secondPasswordText)){
            warningText.setText("");

            String hashedChangePassword = PasswordHasher.passwordHasher(firstPasswordText);
            String verify = "UPDATE login SET password=? WHERE name = ?";

            Connection conn = DatabaseConnector.getConnection();

            PreparedStatement ps = conn.prepareStatement(verify);
            ps.setString(1, hashedChangePassword);
            ps.setString(2, LoginController.name);

            ps.executeUpdate();
            ps.close();
            conn.close();

            warningText.setText("Changed corretly");
            warningText.setTextFill(Color.GREEN);
        }else{
            warningText.setText("Given fields are wrong");
            warningText.setTextFill(Color.RED);
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedText.setText("Logged as : " + LoginController.name);
        warningText.setText("");
    }

}
