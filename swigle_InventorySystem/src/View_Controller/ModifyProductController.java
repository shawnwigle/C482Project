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
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField productIdText;
    @FXML
    private TextField productNameText;
    @FXML
    private TextField productInvText;
    @FXML
    private TextField productPriceText;
    @FXML
    private TextField productMaxText;
    @FXML
    private TextField productMinText;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonCancel;
    @FXML
    private TableView allPartsTable;
    @FXML
    private TableColumn<Part, Integer> availablePartID;
    @FXML
    private TableColumn<Part, String> availablePartName;
    @FXML
    private TableColumn<Part, Integer> availablePartInventoryLevel;
    @FXML
    private TableColumn<Part, Double> availablePartPrice;
    @FXML
    private TableView<Part> currentPartsTable;
    @FXML
    private TableColumn<Part, Integer> associatedPartID;
    @FXML
    private TableColumn<Part, String> associatedPartName;
    @FXML
    private TableColumn<Part, Integer> associatedPartInventoryLevel;
    @FXML
    private TableColumn<Part, Double> associatedPartPrice;
    @FXML
    private Button deleteProductButton;
    @FXML
    private Button addProductButton;
    @FXML
    private Button searchProductButton;
    @FXML
    private TextField productSearchText;
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
        currentPartsTable.setItems(Product.getAssociatedParts());
        currentParts = Product.getAssociatedParts();


    }

    @FXML
    void addProductHandler(MouseEvent event) {
        Part selectedPart = (Part) allPartsTable.getSelectionModel().getSelectedItem();
        currentParts.add(selectedPart);
        currentPartsTable.setItems(currentParts);
    }

    @FXML
    void cancelProductHandler(MouseEvent event) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScreenScene);
        window.show();
    }

    @FXML
    void deleteProductHandler(MouseEvent event) {
        Part partToDelete = currentPartsTable.getSelectionModel().getSelectedItem();
        currentPartsTable.getItems().remove(partToDelete);
    }

    @FXML
    void saveProductHandler(MouseEvent event) throws IOException {
        //TODO: ADD VALIDATION
        boolean saved;
        String name = productNameText.getText();
        int inv = Integer.parseInt(productInvText.getText());
        double price = Double.parseDouble(productPriceText.getText());
        int max = Integer.parseInt(productMaxText.getText());
        int min = Integer.parseInt(productMinText.getText());
        ObservableList<Part> parts = currentPartsTable.getItems();

        currentProduct.setName(name);
        currentProduct.setInv(inv);
        currentProduct.setPrice(price);
        currentProduct.setMax(max);
        currentProduct.setMin(min);
        Product.setAssociatedParts(parts);


        saved = true;
        if (saved) {
            Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene mainScreenScene = new Scene(mainScreenParent);

            //This line gets the Stage information
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
        availablePartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        availablePartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        currentPartsTable.setItems(Product.getAssociatedParts());
        associatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


    }
}
