package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartController {

    @FXML private RadioButton radioInHouse;
    @FXML private RadioButton radioOutsourced;
    @FXML private TextField partIdText;
    @FXML private TextField partNameText;
    @FXML private TextField partInvText;
    @FXML private TextField partPriceText;
    @FXML private TextField partMaxText;
    @FXML private TextField partMinText;
    @FXML private TextField partCompanyText;
    @FXML private TextField partMachineText;
    @FXML private Label labelMachineID;
    @FXML private Label labelCompanyName;
    @FXML private Button buttonSave;
    @FXML private Button buttonCancel;
    private boolean inHouse = true;

    /**
     * When this method is called it enables the Machine fields and disables the company fields
     */
    @FXML
    void inHouseHandler(ActionEvent event) {
        if (radioInHouse.isSelected() == true) {
            labelCompanyName.setOpacity(0);
            partCompanyText.setOpacity(0);
            partCompanyText.setEditable(false);
            partCompanyText.setDisable(true);
            labelMachineID.setOpacity(1);
            partMachineText.setOpacity(1);
            partMachineText.setEditable(true);
            partMachineText.setDisable(false);
            inHouse = true;
        }
    }

    /**
     * When this method is called it enables the company fields and disables the machine fields
     */
    @FXML
    void outsourcedHandler(ActionEvent event) {
        if (radioOutsourced.isSelected() == true) {
            labelCompanyName.setOpacity(1);
            partCompanyText.setOpacity(1);
            partCompanyText.setEditable(true);
            partCompanyText.setDisable(false);
            labelMachineID.setOpacity(0);
            partMachineText.setOpacity(0);
            partMachineText.setEditable(false);
            partMachineText.setDisable(true);
            inHouse = false;
        }
    }

    /**
     * When this method is called it will cancel the window and go back to the main screen
     */
    @FXML
    void partCancelHandler(MouseEvent event) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScreenScene);
        window.show();
    }

    /**
     * When this method is called it will check the form for errors and save it when all errors are corrected
     */
    @FXML
    void partSaveHandler(MouseEvent event) throws IOException {
        Alert a = new Alert(Alert.AlertType.NONE);
        boolean saved = false;
        try {
            String name = partNameText.getText();
            double price = Double.parseDouble(partPriceText.getText());
            int stock = Integer.parseInt(partInvText.getText());
            int min = Integer.parseInt(partMinText.getText());
            int max = Integer.parseInt(partMaxText.getText());


            // max must be at least one
            if (max >= 1) {
                // max must be greater than min
                if (max >= min) {
                    // ensure that Inv is between min and max
                    if (stock <= max && stock >= min) {
                        // instantiate data into an object and add to table
                        if (inHouse) {
                            int machine = Integer.parseInt(partMachineText.getText());
                            Inventory.addPart(new InHouse(name, price, stock, min, max, machine));
                        } else {
                            String company = partCompanyText.getText();
                            Inventory.addPart(new Outsourced(name, price, stock, min, max, company));
                        }
                        saved = true;
                    } else {
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.setContentText("Inv must be between max and min.");
                        a.show();
                        partInvText.setText("");
                    }
                } else {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Max must be greater than min");
                    a.show();
                    partMaxText.setText("");
                    partMinText.setText("");
                }
            } else {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Max must be greater than or equal to 1");
                a.show();
                partMaxText.setText("");
            }
            if (saved == true) {
                Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene mainScreenScene = new Scene(mainScreenParent);

                //This line gets the Stage information
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(mainScreenScene);
                window.show();
            }
        } catch (Exception e) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Something went wrong. Ensure all fields are filled out properly");
            a.show();
            partMinText.setText("");
            partInvText.setText("");
            partPriceText.setText("");
            partNameText.setText("");
            partMaxText.setText("");
            partMachineText.setText("");
            partCompanyText.setText("");
        }
    }

}