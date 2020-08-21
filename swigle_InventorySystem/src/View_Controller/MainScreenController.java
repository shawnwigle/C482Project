package View_Controller;

import Model.*;
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


public class MainScreenController {

    static boolean entered;
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button searchPartButton; // Value injected by FXMLLoader
    @FXML private TextField searchPartText; // Value injected by FXMLLoader
    @FXML private TableView<Part> partTable; // Value injected by FXMLLoader
    @FXML private TableColumn<Part, Integer> partID; // Value injected by FXMLLoader
    @FXML private TableColumn<Part, String> partName; // Value injected by FXMLLoader
    @FXML private TableColumn<Part, Integer> partInventoryLevel; // Value injected by FXMLLoader
    @FXML private TableColumn<Part, Double> partPrice; // Value injected by FXMLLoader
    @FXML private Button addPartButton; // Value injected by FXMLLoader
    @FXML private Button modifyPartButton; // Value injected by FXMLLoader
    @FXML private Button deletePartButton; // Value injected by FXMLLoader
    @FXML private Button searchProductButton; // Value injected by FXMLLoader
    @FXML private TextField searchProductText; // Value injected by FXMLLoader
    @FXML private TableView<Product> productTable; // Value injected by FXMLLoader
    @FXML private TableColumn<Product, Integer> productID; // Value injected by FXMLLoader
    @FXML private TableColumn<Product, String> productName; // Value injected by FXMLLoader
    @FXML private TableColumn<Product, Integer> productInventoryLevel; // Value injected by FXMLLoader
    @FXML private TableColumn<Product, Double> productPrice; // Value injected by FXMLLoader
    @FXML private Button addProductButton; // Value injected by FXMLLoader
    @FXML private Button modifyProductButton; // Value injected by FXMLLoader
    @FXML private Button deleteProductButton; // Value injected by FXMLLoader
    @FXML private Button exitButton; // Value injected by FXMLLoader

    public void changeScenes(MouseEvent event, String scene) throws IOException {
        // Load in the add part fxml document and instantiate the scene object
        Parent addPartParent = FXMLLoader.load(getClass().getResource(scene));
        Scene addPartScene = new Scene(addPartParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }

//    Parts Section

    /**
     * When this method id called, it will change scenes to the "AddPart" scene
     *
     * @param event
     */
    @FXML
    void addPartHandler(MouseEvent event) throws IOException {
        changeScenes(event, "AddPart.fxml");

    }

    /**
     * When this method id called, it will change scenes to the "ModifyPart" scene
     *
     * @param event
     */
    @FXML
    void modifyPartHandler(MouseEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyPart.fxml"));
            Parent modifyPartParent = loader.load();
            Scene modifyPartScene = new Scene(modifyPartParent);

            ModifyPartController controller = loader.getController();
            controller.populateData(partTable.getSelectionModel().getSelectedItem());

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(modifyPartScene);
            window.show();
        } catch (RuntimeException runtimeException) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("You must select a part in order to modify!");
            a.show();
        }
    }

    @FXML
    void deletePartHandler(MouseEvent event) {
        Part selectedItem = partTable.getSelectionModel().getSelectedItem();
        partTable.getItems().remove(selectedItem);
    }

    @FXML
    void searchPartHandler(MouseEvent event) {
        String searchItem = searchPartText.getText().toLowerCase();
        ObservableList<Part> searchParts = FXCollections.observableArrayList();
        boolean found = false;
        for (Part p : Inventory.getAllParts()) {
            if (p.getName() != null && p.getName().toLowerCase().contains(searchItem)) {
                found = true;
                searchParts.add(p);
            }
            else if (String.valueOf(p.getId()).contains(searchItem)) {
                found = true;
                searchParts.add(p);
            }
            else if (String.valueOf(p.getPrice()).contains(searchItem)) {
                found = true;
                searchParts.add(p);
            }
            else if (String.valueOf(p.getStock()).contains(searchItem)) {
                found = true;
                searchParts.add(p);
            }
        }
        if (((searchPartText.getText() == null) || searchPartText.getText().isEmpty()) && !searchPartText.isDisabled()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Information");
            alert.setHeaderText("Error!");
            alert.setContentText("Please enter search criteria!");
            alert.showAndWait();
        } else if (found && !searchPartText.isDisabled()) {
            partTable.setItems(searchParts);
            searchPartButton.setText("Clear");
            searchPartText.setText("");
            searchPartText.setDisable(true);
        } else if (found && searchPartText.isDisabled()) {
            searchPartButton.setText("Search");
            searchPartText.setText("");
            searchPartText.setDisable(false);
            partTable.setItems(searchParts);
        } else {
            searchPartButton.setText("Search");
            searchPartText.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Information");
            alert.setHeaderText("Error!");
            alert.setContentText("Part not found! :-(");
            alert.showAndWait();
        }

    }

    //    Product Section
    @FXML
    void addProductHandler(MouseEvent event) throws IOException {
        changeScenes(event, "AddProduct.fxml");
    }

    @FXML
    void modifyProductHandler(MouseEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyProduct.fxml"));
            Parent modifyProductParent = loader.load();
            Scene modifyProductScene = new Scene(modifyProductParent);

            ModifyProductController controller = loader.getController();
            controller.populateData(productTable.getSelectionModel().getSelectedItem());

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(modifyProductScene);
            window.show();
        } catch (RuntimeException runtimeException) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("You must select a part in order to modify!");
            a.show();
        }

        @FXML
        void deleteProductHandler (MouseEvent event){

        }

        @FXML
        void searchProductHandler (MouseEvent event){

        String searchItem = searchProductText.getText().toLowerCase();
        ObservableList<Product> searchProducts = FXCollections.observableArrayList();
        boolean found = false;
        for (Product p : Inventory.getAllProducts()) {
            if (p.getName() != null && p.getName().toLowerCase().contains(searchItem)) {
                found = true;
                searchProducts.add(p);
            }
            else if (String.valueOf(p.getId()).contains(searchItem)) {
                found = true;
                searchProducts.add(p);
            }
            else if (String.valueOf(p.getPrice()).contains(searchItem)) {
                found = true;
                searchProducts.add(p);
            }
            else if (String.valueOf(p.getStock()).contains(searchItem)) {
                found = true;
                searchProducts.add(p);
            }
        }
        if (((searchProductText.getText() == null) || searchProductText.getText().isEmpty()) && !searchProductText.isDisabled()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Information");
            alert.setHeaderText("Error!");
            alert.setContentText("Please enter search criteria!");
            alert.showAndWait();
        } else if (found && !searchProductText.isDisabled()) {
            productTable.setItems(searchProducts);
            searchProductButton.setText("Clear");
            searchProductText.setText("");
            searchProductText.setDisable(true);
        } else if (found && searchProductText.isDisabled()) {
            searchProductButton.setText("Search");
            searchProductText.setText("");
            searchProductText.setDisable(false);
            productTable.setItems(searchProducts);
        } else {
            searchProductButton.setText("Search");
            searchProductText.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search Information");
            alert.setHeaderText("Error!");
            alert.setContentText("Part not found! :-(");
            alert.showAndWait();
        }
    }

    //    Exit Button
    @FXML
    void exitHandler(MouseEvent event) {
        Stage window = (Stage) exitButton.getScene().getWindow();
        window.close();
    }

    @FXML
    void initialize() {
        // set up the columns
        if (!entered) {
            Inventory.addPart(new InHouse("test", 33.30, 3, 2, 4, 123));
            Inventory.addPart(new Outsourced("test2", 33.354, 4, 3, 5,
                    "test company"));
            Inventory.addProduct(new Product("test", 33.30, 3, 2, 4));
            Inventory.addProduct(new Product("test2", 33.354, 4, 3, 5));
            entered = true;
        } else {
            partTable.refresh();

        }

        partTable.setItems(Inventory.getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(Inventory.getAllProducts());
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


    }
}
