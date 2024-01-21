package org.yuqoi.managerapp.controllers.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.yuqoi.managerapp.models.Invoice.Invoice;
import org.yuqoi.managerapp.models.Watch.Gender;
import org.yuqoi.managerapp.models.Watch.MechanismType;
import org.yuqoi.managerapp.models.Watch.Watch;
import org.yuqoi.managerapp.utils.DatabaseConnector;
import org.yuqoi.managerapp.utils.pdfgenerator.GeneratePdf;
import org.yuqoi.managerapp.utils.pdfgenerator.PdfSerialGenerator;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
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

    // cutomer textfields
    public TextField customerName;
    public TextField customerAddress;
    public TextField customerCountry;
    public ComboBox paymentBox;


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
    public void printInvoice(MouseEvent mouseEvent) throws MalformedURLException, FileNotFoundException {
        if (!selectedToInvoice.isEmpty()){
            String customerTextName = customerName.getText();
            String customerTextAddress = customerAddress.getText();
            String customerTextCountry = customerCountry.getText();
            if (!customerTextName.isEmpty() && !customerTextAddress.isEmpty() && !customerTextCountry.isEmpty() && paymentBox.getValue() != null){
                new GeneratePdf(selectedToInvoice, PdfSerialGenerator.generateInvoiceNumber(), customerTextName, customerTextAddress, customerTextCountry);

                warningText.setText("Created Invoice");
                warningText.setTextFill(Color.GREEN);

//                SELECT w.watch_name, iw.invoice_id, i.total_amount FROM watches AS w JOIN invoice_watch AS iw ON w.watch_id = iw.watch_id JOIN invoice AS i ON iw.invoice_id = i.invoice_id;
                // insert into invoice table
                try {
                    String query = "INSERT INTO invoice(`invoice_id`, `customer_name`, `total_amount`, `payment_method`) VALUES (DEFAULT,?, ?,?)";
                    Connection conn = DatabaseConnector.getConnection();
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setString(1, customerTextName);
                    statement.setDouble(2, sum);
                    statement.setString(3, (String) paymentBox.getValue());

                    statement.executeUpdate();



                    statement.close();
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("przeslano");


            }else{
                warningText.setText("Fields are null");
                warningText.setTextFill(Color.RED);
            }



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
        paymentBox.setItems(FXCollections.observableArrayList("CARD", "CASH"));


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
