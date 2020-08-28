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
import java.util.Optional;


public class MainScreenController {

    static boolean entered;

    @FXML private Button searchPartButton;
    @FXML private TextField searchPartText;
    @FXML private TableView<Part> partTable;
    @FXML private TableColumn<Part, Integer> partID;
    @FXML private TableColumn<Part, String> partName;
    @FXML private TableColumn<Part, Integer> partInventoryLevel;
    @FXML private TableColumn<Part, Double> partPrice;
    @FXML private Button searchProductButton;
    @FXML private TextField searchProductText;
    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, Integer> productID;
    @FXML private TableColumn<Product, String> productName;
    @FXML private TableColumn<Product, Integer> productInventoryLevel;
    @FXML private TableColumn<Product, Double> productPrice;
    @FXML private Button exitButton;

    public void changeScenes(MouseEvent event, String scene) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource(scene));
        Scene addPartScene = new Scene(addPartParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }

//    Parts Section
    @FXML
    void addPartHandler(MouseEvent event) throws IOException {
        changeScenes(event, "AddPart.fxml");

    }

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

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Part Deletion");
        alert.setHeaderText("You are about to delete the Part: " + selectedItem.getName());
        alert.setContentText("Are you sure you want to do this?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){
            Inventory.deletePart(selectedItem);
            System.out.println("Part: " + selectedItem.getName() + " was deleted");
        } else {
            System.out.println("Deletion of part: " + selectedItem.getName() + " was cancelled.");
        }
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
            a.setContentText("You must select a product in order to modify!");
            a.show();
        }
    }

    @FXML
    void deleteProductHandler(MouseEvent event) {
        Product selectedItem = productTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Part Deletion");
        alert.setHeaderText("You are about to delete the Product: " + selectedItem.getName());
        alert.setContentText("Are you sure you want to do this?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){
            Inventory.deleteProduct(selectedItem);
            System.out.println("Product: " + selectedItem.getName() + " was deleted");
        } else {
            System.out.println("Deletion of Product: " + selectedItem.getName() + " was cancelled.");
        }

    }

    @FXML
    void searchProductHandler(MouseEvent event) {
        String searchItem = searchProductText.getText().toLowerCase();
        ObservableList<Product> searchProducts = FXCollections.observableArrayList();
        boolean found = false;
        for (Product p : Inventory.getAllProducts()) {
            if (p.getName() != null && p.getName().toLowerCase().contains(searchItem)) {
                found = true;
                searchProducts.add(p);
            } else if (String.valueOf(p.getId()).contains(searchItem)) {
                found = true;
                searchProducts.add(p);
            } else if (String.valueOf(p.getPrice()).contains(searchItem)) {
                found = true;
                searchProducts.add(p);
            } else if (String.valueOf(p.getInv()).contains(searchItem)) {
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
            alert.setContentText("Product not found! :-(");
            alert.showAndWait();
        }
    }

    @FXML
    void exitHandler(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Program exit");
        alert.setHeaderText("You are about to exit the program");
        alert.setContentText("Is this really what you want to do?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK){
            Stage window = (Stage) exitButton.getScene().getWindow();
            window.close();
        } else {
            System.out.println("Program exit was cancelled!");
        }

    }

    @FXML
    void initialize() {
        // set up the columns
        if (!entered) {
            // In House part test
            InHouse inHouse1 = new InHouse();
            inHouse1.setName("test_inhouse");
            inHouse1.setPrice(22.99);
            inHouse1.setInv(8);
            inHouse1.setMax(12);
            inHouse1.setMin(1);
            inHouse1.setMachineId(123);
            Inventory.addPart(inHouse1);
            System.out.println("Part: " + inHouse1.getName() + " was added");

            // Outsourced part test
            Outsourced outsourced1 = new Outsourced();
            outsourced1.setName("test_outsourced");
            outsourced1.setPrice(35.47);
            outsourced1.setInv(4);
            outsourced1.setMax(12);
            outsourced1.setMin(1);
            outsourced1.setCompanyName("company");
            Inventory.addPart(outsourced1);
            System.out.println("Part: " + outsourced1.getName() + " was added");

            // Product test
            Product test = new Product();
            test.setName("test_product");
            test.setInv(2);
            test.setMax(3);
            test.setMin(1);
            test.setPrice(35.35);
            Inventory.addProduct(test);
            System.out.println("Product: " + test.getName() + " was added");

            entered = true;
        } else {
            partTable.refresh();
        }

        partTable.setItems(Inventory.getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inv"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(Inventory.getAllProducts());
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inv"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


    }
}
