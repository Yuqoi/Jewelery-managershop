package org.yuqoi.managerapp.controllers.panels;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.yuqoi.managerapp.utils.DatabaseConnector;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    // WARNING TEXT
    public Label warningText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPanelGenderBox.setItems(FXCollections.observableArrayList("MALE", "FEMALE"));
        addPanelMechanismBox.setItems(FXCollections.observableArrayList("Quartz", "Mechanical", "Automatic", "HandWinding", "Kinetic", "SpringDrive", "Tourbillon", "Digital", "SolarPowered" , "RadioControlled"));

        closeWindowBtn.setOnMouseClicked(event -> {
            ((Stage) closeWindowBtn.getScene().getWindow()).close();
        });
        applyDataBtn.setOnMouseClicked(event -> {
            // we have to check if the given data is correct and isnt null
            // if it is good we send data to database
            // if its not then me send a warning text that fields are incorrect
            if (addPanelName.getText().isBlank() || addPanelBrand.getText().isBlank() || addPanelGenderBox.getValue() == null || addPanelMPN.getText().isEmpty() || addPanelMechanismBox.getValue() == null || addPanelPrice.getText().isEmpty()){
                warningText.setText("The given data is null or blank");
                warningText.setTextFill(Color.RED);
            }else{
                if (checkIfDouble(addPanelPrice.getText())){
                    // we have to send data to database

                    try {
                        PreparedStatement statement = getPreparedStatement();

                        statement.executeUpdate();

                        warningText.setText("Data has been sent to database");
                        warningText.setTextFill(Color.GREEN);

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                }
            }
        });
    }

    private PreparedStatement getPreparedStatement() throws SQLException {
        String sql = "INSERT INTO watches(watch_id, watch_name, brand, sex, MPN, mechanism_type, price) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";

        Connection conn = DatabaseConnector.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, addPanelName.getText());
        statement.setString(2, addPanelBrand.getText());
        statement.setString(3, (String) addPanelGenderBox.getValue());
        statement.setString(4, addPanelMPN.getText());
        statement.setString(5, (String) addPanelMechanismBox.getValue());
        statement.setDouble(6, Double.parseDouble(addPanelPrice.getText()));
        return statement;
    }

    private boolean checkIfDouble(String str){
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
