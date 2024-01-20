package org.yuqoi.managerapp.controllers.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.yuqoi.managerapp.models.Watch.Gender;
import org.yuqoi.managerapp.models.Watch.MechanismType;
import org.yuqoi.managerapp.models.Watch.Watch;
import org.yuqoi.managerapp.utils.DatabaseConnector;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    // main table
    public TableView invoiceTable;

    // table columns
    public TableColumn nameColumn;
    public TableColumn priceColumn;

    // labels
    public Label priceField;
    public Label selectedItemsField;
    public Label warningText;



    // list
    ObservableList<Watch> WatchList = FXCollections.observableArrayList();
    List<Watch> selectedToInvoice = new ArrayList<>();

    private double sum = 0;
    private int count = 0;


    // adds selected watch to list then removes it from the table
    // prints on the label what
    public void addWatchToInvoice(MouseEvent mouseEvent) {
        Watch selectedWatch = (Watch) invoiceTable.getSelectionModel().getSelectedItem();

        // add selected watch to list and remove it from table
        selectedToInvoice.add(selectedWatch);
        WatchList.remove(selectedWatch);

        // add price and number of selected watches
        sum += selectedWatch.getPrice();
        count += 1;

        priceField.setText(String.valueOf(sum));
        selectedItemsField.setText(String.valueOf(count));
    }


    // prints invoice from selected items cannot be null
    public void printInvoice(MouseEvent mouseEvent) {
        if (!selectedToInvoice.isEmpty()){
            System.out.println(selectedToInvoice.toString());
        }else{
            warningText.setTextFill(Color.RED);
            warningText.setText("Select items!");
        }

    }

    // leaves the window
    public void leaveWindow(MouseEvent mouseEvent) {
            ((Stage) ((Button) mouseEvent.getSource()).getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priceField.setText("");
        selectedItemsField.setText("");

        loadDate();
        initializeTable();
    }

    private void initializeTable(){
        WatchList.clear();
        try {
            Connection conn = DatabaseConnector.getConnection();
            String query = "SELECT * FROM watches";
            PreparedStatement prepStatement = conn.prepareStatement(query);
            ResultSet resultSet = prepStatement.executeQuery();

            while (resultSet.next()){
                Gender genderVal =  Gender.valueOf(resultSet.getString("sex"));
                MechanismType mechanismVal =  MechanismType.valueOf(resultSet.getString("mechanism_type"));
                WatchList.add(new Watch(
                        resultSet.getInt("watch_id"),
                        resultSet.getString("watch_name"),
                        resultSet.getString("brand"),
                        Gender.valueOf(genderVal.name()),
                        resultSet.getString("MPN"),
                        MechanismType.valueOf(mechanismVal.name()),
                        resultSet.getDouble("price")
                ));
                invoiceTable.setItems(WatchList);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDate(){
        // make a connection to database

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("watchName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
    //SELECT watch_name, COUNT(watch_name) AS total_quantity FROM watches GROUP BY watch_name;
}
