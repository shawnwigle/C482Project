package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    //fields
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    //constructor

    //    public Product( String name, double price, int stock, int min, int max, ObservableList<Part> associatedParts) {
    public Product() {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.setAssociatedParts(associatedParts);
    }

    //getters & setters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }

    public static ObservableList getAssociatedParts() {
        return associatedParts;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        Product.associatedParts.addAll(associatedParts);
    }

    //other
    public void addAssociatedPart(Part part) {

    }

    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return false;
    }


}
