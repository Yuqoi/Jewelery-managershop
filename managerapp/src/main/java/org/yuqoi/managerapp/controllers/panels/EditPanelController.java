package org.yuqoi.managerapp.controllers.panels;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.yuqoi.managerapp.models.Gender;
import org.yuqoi.managerapp.models.MechanismType;
import org.yuqoi.managerapp.models.Watch;
import org.yuqoi.managerapp.utils.DatabaseConnector;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class EditPanelController implements Initializable {

    // textfields
    public TextField editPanelName;
    public TextField editPanelBrand;
    public ComboBox editPanelGenderBox;
    public TextField editPanelMPN;
    public ComboBox editPanelMechanismBox;
    public TextField editPanelPrice;

    // buttons
    public Button applyDataBtn;
    public Button closeWindowBtn;

    // warning text
    public Label warningText;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editPanelGenderBox.setItems(FXCollections.observableArrayList("MALE", "FEMALE"));
        editPanelMechanismBox.setItems(FXCollections.observableArrayList("Quartz", "Mechanical", "Automatic", "HandWinding", "Kinetic", "SpringDrive", "Tourbillon", "Digital", "SolarPowered" , "RadioControlled"));

        // retrieve data
        Watch data = new Watch().getInstance();

        // set the data
        editPanelName.setText(data.getWatchName());
        editPanelBrand.setText(data.getBrand());
        editPanelGenderBox.setValue(data.getGender());
        editPanelMPN.setText(data.getMpn());
        editPanelMechanismBox.setValue(data.getMechanismType());
        editPanelPrice.setText(String.valueOf(data.getPrice()));

        closeWindowBtn.setOnMouseClicked(event -> {
            ((Stage) closeWindowBtn.getScene().getWindow()).close();
        });
        applyDataBtn.setOnMouseClicked(event -> {
            // check if have we changed something
            // check the columns that has been updated and execute them into data base after executing show text about progress
            if (!(data.getWatchName().equals(editPanelName.getText()) &&
                    data.getBrand().equals(editPanelBrand.getText()) &&
                    data.getGender().equals(editPanelGenderBox.getValue()) &&
                    data.getMpn().equals(editPanelMPN.getText()) &&
                    data.getMechanismType().equals(editPanelMechanismBox.getValue()) &&
                    data.getPrice() == Double.parseDouble(editPanelPrice.getText()))){


                try {
                    // get the changed data and insert it into database
                    int id = data.getWatchId();
                    String sql = "UPDATE watches SET watch_name = ?, brand = ?, MPN = ?, price = ? WHERE watch_id = ?";

                    Connection conn = DatabaseConnector.getConnection();
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, editPanelName.getText());
                    statement.setString(2, editPanelBrand.getText());
//                    statement.setObject(3, (Gender) editPanelGenderBox.getValue());
                    statement.setString(3, editPanelMPN.getText());
//                    statement.setObject(5, (MechanismType) editPanelMechanismBox.getValue());
                    statement.setDouble(4, Double.parseDouble(editPanelPrice.getText()));
                    statement.setInt(5, id);

                    statement.executeUpdate();

                    warningText.setText("Data changed");
                    warningText.setTextFill(Color.GREEN);

                    // TODO CHANGE VALUES FOR ENUM TYPES

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }else {
                warningText.setText("The data hasn't been changed");
                warningText.setTextFill(Color.RED);
            }





        });

    }



}
