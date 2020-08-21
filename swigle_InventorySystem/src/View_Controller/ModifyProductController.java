/**
 * Sample Skeleton for 'ModifyProduct.fxml' Controller Class
 */

package View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.InHouse;
import Model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableColumn<Product, Integer> addProductID;
    @FXML
    private TableColumn<Product, String> addProductName;
    @FXML
    private TableColumn<Product, Integer> addProductInventoryLevel;
    @FXML
    private TableColumn<Product, Double> addProductPrice;
    @FXML
    private TableView associatedPartsTable;
    @FXML
    private TableColumn<Product, Integer> deleteProductID;
    @FXML
    private TableColumn<Product, String> deleteProductName;
    @FXML
    private TableColumn<Product, Integer> deleteProductInventoryLevel;
    @FXML
    private TableColumn<Product, Double> deleteProductPrice;
    @FXML
    private Button deleteProductButton;
    @FXML
    private Button addProductButton;
    @FXML
    private Button searchProductButton;
    @FXML
    private TextField productSearchText;

    @FXML
    void populateData(Product product) {
        // TODO: fix your shit dumbass
//        this.product = product;
        productIdText.setText(Integer.toString(product.getId()));
        productNameText.setText(product.getName());
        productInvText.setText(Integer.toString(product.getStock()));
        productPriceText.setText(Double.toString(product.getPrice()));
        productMaxText.setText(Integer.toString(product.getMax()));
        productMinText.setText(Integer.toString(product.getMin()));

    }

    @FXML
    void cancelProductHandler(MouseEvent event) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScreenScene);
        window.show();
    }

    @FXML
    void deleteProductHandler(MouseEvent event) {

    }

    @FXML
    void saveProductHandler(MouseEvent event) {

    }

    @FXML
    void searchProductHandler(MouseEvent event) {

    }

    @FXML
    void initialize() {

    }
}
