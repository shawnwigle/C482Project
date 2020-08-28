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

    //not used. more robust solution in place
    public Part lookupPart(int partId){
        return allParts.get(partId);
    }

    //not used. more robust solution in place
    public Product lookupProduct (int productId){
        return allProducts.get(productId);
    }

    //not used. more robust solution in place
    public ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> searchParts = FXCollections.observableArrayList();
        for (Part p : Inventory.getAllParts()){
            if (p.getName() != null && p.getName().toLowerCase().contains(partName)) {
                searchParts.add(p);
            }
        }
            return searchParts;
    }

    //not used. more robust solution in place
    public ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> searchProducts = FXCollections.observableArrayList();
        for (Product p : Inventory.getAllProducts()){
            if (p.getName() != null && p.getName().toLowerCase().contains(productName)) {
                searchProducts.add(p);
            }
        }
        return searchProducts;
    }

    public static void updatePart (int index, Part part){
        allParts.set(index, part);
    }

    public static void updateProduct (int index, Product product){
        allProducts.set(index, product);
    }

    public static boolean deletePart (Part selectedPart){

        return allParts.remove(selectedPart);
    }

    public static boolean deleteProduct (Product selectedProduct){

        return allProducts.remove(selectedProduct);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}

