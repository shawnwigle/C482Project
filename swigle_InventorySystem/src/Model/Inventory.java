package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
//    public Part lookupPart(int partId){return something;}
//    public Product lookupProduct (int productId){return something;}
//    public ObservableList<Part> lookupPart(String partName){return something;}
//    public ObservableList<Product> lookupProduct(String productName){return something;}
//
//    public static void updatePart (int index, Part selectedPart){}
//    public static void updateProduct (int index, Product selectedProduct){}
//    public static boolean deletePart (Part selectedPart){return true;}
//    public static boolean deleteProduct (Product selectedProduct){return true;}

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}

