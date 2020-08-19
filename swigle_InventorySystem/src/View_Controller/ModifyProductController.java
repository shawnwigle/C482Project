/**
 * Sample Skeleton for 'ModifyProduct.fxml' Controller Class
 */

package View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModifyProductController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="productIdText"
    private TextField productIdText; // Value injected by FXMLLoader

    @FXML // fx:id="partNameText"
    private TextField partNameText; // Value injected by FXMLLoader

    @FXML // fx:id="partInvText"
    private TextField partInvText; // Value injected by FXMLLoader

    @FXML // fx:id="partPriceText"
    private TextField partPriceText; // Value injected by FXMLLoader

    @FXML // fx:id="partMaxText"
    private TextField partMaxText; // Value injected by FXMLLoader

    @FXML // fx:id="partMinText"
    private TextField partMinText; // Value injected by FXMLLoader

    @FXML // fx:id="buttonSave"
    private Button buttonSave; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCancel"
    private Button buttonCancel; // Value injected by FXMLLoader

    @FXML // fx:id="addPartID"
    private TableColumn<?, ?> addPartID; // Value injected by FXMLLoader

    @FXML // fx:id="addPartName"
    private TableColumn<?, ?> addPartName; // Value injected by FXMLLoader

    @FXML // fx:id="addPartInventoryLevel"
    private TableColumn<?, ?> addPartInventoryLevel; // Value injected by FXMLLoader

    @FXML // fx:id="addPartPrice"
    private TableColumn<?, ?> addPartPrice; // Value injected by FXMLLoader

    @FXML // fx:id="deletePartID"
    private TableColumn<?, ?> deletePartID; // Value injected by FXMLLoader

    @FXML // fx:id="deletePartName"
    private TableColumn<?, ?> deletePartName; // Value injected by FXMLLoader

    @FXML // fx:id="deletePartInventoryLevel"
    private TableColumn<?, ?> deletePartInventoryLevel; // Value injected by FXMLLoader

    @FXML // fx:id="deletePartPrice"
    private TableColumn<?, ?> deletePartPrice; // Value injected by FXMLLoader

    @FXML // fx:id="deleteProductButton"
    private Button deleteProductButton; // Value injected by FXMLLoader

    @FXML // fx:id="addProductButton"
    private Button addProductButton; // Value injected by FXMLLoader

    @FXML // fx:id="searchProductButton"
    private Button searchProductButton; // Value injected by FXMLLoader

    @FXML // fx:id="productSearchText"
    private TextField productSearchText; // Value injected by FXMLLoader

    @FXML
    void addProductHandler(MouseEvent event) {

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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert productIdText != null : "fx:id=\"productIdText\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert partNameText != null : "fx:id=\"partNameText\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert partInvText != null : "fx:id=\"partInvText\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert partPriceText != null : "fx:id=\"partPriceText\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert partMaxText != null : "fx:id=\"partMaxText\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert partMinText != null : "fx:id=\"partMinText\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert buttonSave != null : "fx:id=\"buttonSave\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert buttonCancel != null : "fx:id=\"buttonCancel\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert addPartID != null : "fx:id=\"addPartID\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert addPartName != null : "fx:id=\"addPartName\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert addPartInventoryLevel != null : "fx:id=\"addPartInventoryLevel\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert addPartPrice != null : "fx:id=\"addPartPrice\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert deletePartID != null : "fx:id=\"deletePartID\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert deletePartName != null : "fx:id=\"deletePartName\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert deletePartInventoryLevel != null : "fx:id=\"deletePartInventoryLevel\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert deletePartPrice != null : "fx:id=\"deletePartPrice\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert deleteProductButton != null : "fx:id=\"deleteProductButton\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert addProductButton != null : "fx:id=\"addProductButton\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert searchProductButton != null : "fx:id=\"searchProductButton\" was not injected: check your FXML file 'ModifyProduct.fxml'.";
        assert productSearchText != null : "fx:id=\"productSearchText\" was not injected: check your FXML file 'ModifyProduct.fxml'.";

    }
}
