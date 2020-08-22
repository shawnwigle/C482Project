package View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private TableView<Part> associatedPartsTable;
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
    private ObservableList<Part> selectedParts = FXCollections.observableArrayList();
    private Product product;

    @FXML
    void populateData(Product product) {
        this.product = product;
        productIdText.setText(Integer.toString(product.getId()));
        productNameText.setText(product.getName());
        productInvText.setText(Integer.toString(product.getStock()));
        productPriceText.setText(Double.toString(product.getPrice()));
        productMaxText.setText(Integer.toString(product.getMax()));
        productMinText.setText(Integer.toString(product.getMin()));
        associatedPartsTable.setItems(product.getAssociatedParts());

    }

    @FXML
    void addProductHandler(MouseEvent event) {
        Part selectedPart = (Part) allPartsTable.getSelectionModel().getSelectedItem();
        selectedParts.add(selectedPart);
        associatedPartsTable.setItems(selectedParts);
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
        Part partsToDelete = associatedPartsTable.getSelectionModel().getSelectedItem();
        product.deleteAssociatedPart(partsToDelete);
    }

    @FXML
    void saveProductHandler(MouseEvent event) {

    }

    @FXML
    void searchProductHandler(MouseEvent event) {

    }

    @FXML
    void initialize() {
        allPartsTable.setItems(Inventory.getAllParts());
        availablePartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        availablePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        availablePartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        availablePartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartsTable.setItems(Product.getAssociatedParts());
        associatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


    }
}
