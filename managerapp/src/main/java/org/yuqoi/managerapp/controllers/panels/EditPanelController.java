package org.yuqoi.managerapp.controllers.panels;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.yuqoi.managerapp.models.Gender;
import org.yuqoi.managerapp.models.MechanismType;
import org.yuqoi.managerapp.models.Watch;

import java.net.URL;
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

    // gotten watch
    private Watch gottenWatch = null;
    private String namee;


    public void setWatch(Watch watch) {
        gottenWatch = watch;

//        gottenWatch = new Watch(watch.watchId, watch.watchName, watch.brand, watch.gender, watch.mpn, watch.mechanismType, watch.price);

    }

    public Watch getWatch(){
        return gottenWatch;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editPanelGenderBox.setItems(FXCollections.observableArrayList("MALE", "FEMALE"));
        editPanelMechanismBox.setItems(FXCollections.observableArrayList("Quartz", "Mechanical", "Automatic", "HandWinding", "Kinetic", "SpringDrive", "Tourbillon", "Digital", "SolarPowered" , "RadioControlled"));
        Watch newWatch = getWatch();
        System.out.println(newWatch.toString());
//        setWatch(gottenWatch);

//        Gender gottenGender = (Gender) editPanelGenderBox.getValue();
//        MechanismType gottenMechanismType = (MechanismType) editPanelMechanismBox.getValue();
//

//

//
//        closeWindowBtn.setOnMouseClicked(event -> {
//            ((Stage) closeWindowBtn.getScene().getWindow()).close();
//        });
//        applyDataBtn.setOnMouseClicked(event -> {
////            we push into database changed values
//        });

    }



}
