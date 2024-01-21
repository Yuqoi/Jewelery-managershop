package org.yuqoi.managerapp.controllers.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.yuqoi.managerapp.models.Invoice.Invoice;
import org.yuqoi.managerapp.models.Invoice.Payment;
import org.yuqoi.managerapp.models.Watch.Gender;
import org.yuqoi.managerapp.models.Watch.MechanismType;
import org.yuqoi.managerapp.models.Watch.Watch;
import org.yuqoi.managerapp.utils.DatabaseConnector;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SalesController implements Initializable {
    public TableView salesList;
    public TableColumn idColumn;
    public TableColumn customerColumn;
    public TableColumn totalamountColumn;
    public TableColumn paymentColumn;

    Connection conn = null;
    ObservableList<Invoice> invoiceObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTable();
        setData();
    }

    public void setTable(){
        conn = DatabaseConnector.getConnection();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        totalamountColumn.setCellValueFactory(new PropertyValueFactory<>("total_amount"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<>("payment"));

    }
    public void setData(){

        try {
            String query = "SELECT * FROM invoice";
            PreparedStatement prepStatement = conn.prepareStatement(query);
            ResultSet resultSet = prepStatement.executeQuery();

            while (resultSet.next()){
                Payment paymentMetod =  Payment.valueOf(resultSet.getString("payment_method"));
                invoiceObservableList.add(new Invoice(
                        resultSet.getInt("invoice_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getDouble("total_amount"),
                        paymentMetod)
                );
                salesList.setItems(invoiceObservableList);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
