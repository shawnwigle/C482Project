package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;


public class ModifyProductController {

    @FXML private TextField productIdText;
    @FXML private TextField productNameText;
    @FXML private TextField productInvText;
    @FXML private TextField productPriceText;
    @FXML private TextField productMaxText;
    @FXML private TextField productMinText;
    @FXML private TableView<Part> allPartsTable;
    @FXML private TableColumn<Part, Integer> availablePartID;
    @FXML private TableColumn<Part, String> availablePartName;
    @FXML private TableColumn<Part, Integer> availablePartInventoryLevel;
    @FXML private TableColumn<Part, Double> availablePartPrice;
    @FXML private TableView<Part> currentPartsTable;
    @FXML private TableColumn<Part, Integer> associatedPartID;
    @FXML private TableColumn<Part, String> associatedPartName;
    @FXML private TableColumn<Part, Integer> associatedPartInventoryLevel;
    @FXML private TableColumn<Part, Double> associatedPartPrice;
    @FXML private Button searchProductButton;
    @FXML private TextField productSearchText;
    private ObservableList<Part> currentParts = FXCollections.observableArrayList();
    private Product currentProduct;

    @FXML
    void populateData(Product product) {
        this.currentProduct = product;
        productIdText.setText(Integer.toString(currentProduct.getId()));
        productNameText.setText(currentProduct.getName());
        productInvText.setText(Integer.toString(currentProduct.getInv()));
        productPriceText.setText(Double.toString(currentProduct.getPrice()));
        productMaxText.setText(Integer.toString(currentProduct.getMax()));
        productMinText.setText(Integer.toString(currentProduct.getMin()));
        currentPartsTable.setItems(product.getAssociatedParts());
        currentParts = product.getAssociatedParts();
    }

    @FXML
    void addProductHandler(MouseEvent event) {
        Part selectedPart = allPartsTable.getSelectionModel().getSelectedItem();
        currentParts.add(selectedPart);
        currentPartsTable.setItems(currentParts);
    }

    @FXML
    void cancelProductHandler(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Product Modification");
        alert.setHeaderText("You are about to cancel the modification of the Product: " + currentProduct.getName());
        alert.setContentText("Are you sure you want to do this?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("Product modification was cancelled");
            Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene mainScreenScene = new Scene(mainScreenParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(mainScreenScene);
            window.show();
        } else {
            System.out.println("Product modification will continue");
        }
    }

    @FXML
    void deleteAssociatedPartHandler(MouseEvent event) {
        Part partToDelete = currentPartsTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Product Part deletion");
        alert.setHeaderText("You are about to delete the association with Part: " + partToDelete.getName());
        alert.setContentText("Are you sure you want to do this?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("Association with Part: " + partToDelete.getName() + " removed.");
            currentParts.remove(partToDelete);
        } else {
            System.out.println("Product Part deletion was cancelled");
        }
    }

    @FXML void saveProductHandler(MouseEvent event) throws IOException {
        int id = currentProduct.getId();
        String name = productNameText.getText();
        int inv = Integer.parseInt(productInvText.getText());
        double price = Double.parseDouble(productPriceText.getText());
        int max = Integer.parseInt(productMaxText.getText());
        int min = Integer.parseInt(productMinText.getText());
        ObservableList<Part> parts = currentPartsTable.getItems();
        boolean saved = false;
        Alert a = new Alert(Alert.AlertType.ERROR);

        if (max >= min) {
            saved = true;

            currentProduct.setName(name);
            currentProduct.setInv(inv);
            currentProduct.setPrice(price);
            currentProduct.setMax(max);
            currentProduct.setMin(min);

            Inventory.updateProduct(id, currentProduct);
            currentProduct.setAssociatedParts(parts);
            System.out.println("Product: " + currentProduct.getName() + " was modified.");
        } else{
            a.setContentText("Max must be greater than min!");
            a.show();
            productMaxText.setText("");
            productMinText.setText("");
        }

        if (saved) {
            Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene mainScreenScene = new Scene(mainScreenParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(mainScreenScene);
            window.show();
        }

    }

    @FXML
    void searchProductHandler(MouseEvent event) {
        String searchItem = productSearchText.getText().toLowerCase();
        ObservableList<Part> searchParts = FXCollections.observableArrayList();
        boolean found = false;
        for (Part p : Inventory.getAllParts()) {
            if (p.getName() != null && p.getName().toLowerCase().contains(searchItem)) {
                found = true;
                searchParts.add(p);
            } else if (String.valueOf(p.getId()).contains(searchItem)) {
                found = true;
                searchParts.add(p);
            } else if (String.valueOf(p.getPrice()).contains(searchItem)) {
                found = true;
                searchParts.add(p);
            } else if (String.valueOf(p.getInv()).contains(searchItem)) {
                found = true;
                searchParts.add(p);
            }
        }
        if (((productSearchText.getText() == null) || productSearchText.getText().isEmpty()) && !productSearchText.isDisabled()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Information");
            alert.setHeaderText("Error!");
            alert.setContentText("Please enter search criteria!");
            alert.showAndWait();
        } else if (found && !productSearchText.isDisabled()) {
            allPartsTable.setItems(searchParts);
            searchProductButton.setText("Clear");
            productSearchText.setText("");
            productSearchText.setDisable(true);
        } else if (found && productSearchText.isDisabled()) {
            searchProductButton.setText("Search");
            productSearchText.setText("");
            productSearchText.setDisable(false);
            allPartsTable.setItems(searchParts);
        } else {
            searchProductButton.setText("Search");
            productSearchText.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Information");
            alert.setHeaderText("Error!");
            alert.setContentText("Part not found! :-(");
            alert.showAndWait();
        }
    }

    @FXML
    void initialize() {
        allPartsTable.setItems(Inventory.getAllParts());
        availablePartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        availablePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        availablePartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inv"));
        availablePartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        currentPartsTable.setItems(currentParts);
        associatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inv"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


    }
}
