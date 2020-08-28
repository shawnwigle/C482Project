package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class ModifyPartController {
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
    @FXML private Label labelCompanyName;
    @FXML private Label labelMachineID;
    private Part selectedPart;

    @FXML
    void inHouseHandler() {
        if (radioInHouse.isSelected()) {
            labelCompanyName.setOpacity(0);
            partCompanyText.setOpacity(0);
            partCompanyText.setEditable(false);
            partCompanyText.setDisable(true);
            labelMachineID.setOpacity(1);
            partMachineText.setOpacity(1);
            partMachineText.setEditable(true);
            partMachineText.setDisable(false);
        }
    }

    @FXML
    void outsourcedHandler() {
        if (radioOutsourced.isSelected()) {
            labelCompanyName.setOpacity(1);
            partCompanyText.setOpacity(1);
            partCompanyText.setEditable(true);
            partCompanyText.setDisable(false);
            labelMachineID.setOpacity(0);
            partMachineText.setOpacity(0);
            partMachineText.setEditable(false);
            partMachineText.setDisable(true);
        }
    }

    public void populateData(Part part) {
        this.selectedPart = part;
        if (part instanceof InHouse) {
            radioInHouse.setSelected(true);
            inHouseHandler();
            InHouse inHousePart = (InHouse) part;
            partIdText.setText(Integer.toString(inHousePart.getId()));
            partNameText.setText(inHousePart.getName());
            partInvText.setText(Integer.toString(inHousePart.getInv()));
            partPriceText.setText(Double.toString(inHousePart.getPrice()));
            partMaxText.setText(Integer.toString(inHousePart.getMax()));
            partMinText.setText(Integer.toString(inHousePart.getMin()));
            partMachineText.setText(Integer.toString(inHousePart.getMachineId()));

        } else {
            radioOutsourced.setSelected(true);
            outsourcedHandler();
            Outsourced outsourcedPart = (Outsourced) part;
            partIdText.setText(Integer.toString(outsourcedPart.getId()));
            partNameText.setText(outsourcedPart.getName());
            partInvText.setText(Integer.toString(outsourcedPart.getInv()));
            partPriceText.setText(Double.toString(outsourcedPart.getPrice()));
            partMaxText.setText(Integer.toString(outsourcedPart.getMax()));
            partMinText.setText(Integer.toString(outsourcedPart.getMin()));
            partCompanyText.setText(outsourcedPart.getCompanyName());
        }
    }

    @FXML
    void partCancelHandler(MouseEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Part Modification");
        alert.setHeaderText("You are about to cancel your modifications! \nAll changes will be lost!!!");
        alert.setContentText("Are you sure you want to do this?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("Part modification was cancelled");
            Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene mainScreenScene = new Scene(mainScreenParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(mainScreenScene);
            window.show();
        } else {
            System.out.println("Part modification will continue");
        }

    }

    @FXML
    void partSaveHandler(MouseEvent event) throws IOException {
        Alert a = new Alert(Alert.AlertType.NONE);
        boolean saved = false;
        try {
            int id = selectedPart.getId();
            String name = partNameText.getText();
            double price = Double.parseDouble(partPriceText.getText());
            int inv = Integer.parseInt(partInvText.getText());
            int min = Integer.parseInt(partMinText.getText());
            int max = Integer.parseInt(partMaxText.getText());
            String company = partCompanyText.getText();
            int machine = Integer.parseInt(partMachineText.getText());
            boolean inhouse = radioInHouse.isSelected();
            boolean outsourced = radioOutsourced.isSelected();

            // max must be greater than min
            if (max >= min && inhouse) {
                InHouse modifiedInHousePart = new InHouse(id);
                modifiedInHousePart.setName(name);
                modifiedInHousePart.setPrice(price);
                modifiedInHousePart.setInv(inv);
                modifiedInHousePart.setMin(min);
                modifiedInHousePart.setMax(max);
                modifiedInHousePart.setMachineId(machine);
                Inventory.updatePart(id, modifiedInHousePart);
                saved = true;
            } else if (max >= min && outsourced) {
                Outsourced modifiedOutsourcedPart = new Outsourced(id);
                modifiedOutsourcedPart.setName(name);
                modifiedOutsourcedPart.setPrice(price);
                modifiedOutsourcedPart.setInv(inv);
                modifiedOutsourcedPart.setMin(min);
                modifiedOutsourcedPart.setMax(max);
                modifiedOutsourcedPart.setCompanyName(company);
                Inventory.updatePart(id, modifiedOutsourcedPart);
                saved = true;
            } else {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Max must be greater than min");
                a.show();
                partMaxText.setText("");
                partMinText.setText("");
            }
            if (saved) {
                Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene mainScreenScene = new Scene(mainScreenParent);
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