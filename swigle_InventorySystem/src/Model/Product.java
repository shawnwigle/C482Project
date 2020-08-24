package Model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    //fields
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private final int id;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inv;
    private final IntegerProperty min;
    private final IntegerProperty max;

    //constructor
    public Product() {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.inv = new SimpleIntegerProperty();
        this.min = new SimpleIntegerProperty();
        this.max = new SimpleIntegerProperty();
        associatedParts = Product.getAssociatedParts();

    }

    public static ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    public static void setAssociatedParts(ObservableList<Part> selectedParts) {
        Product.associatedParts = selectedParts;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public int getInv() {
        return inv.get();
    }

    public void setInv(int inv) {
        this.inv.set(inv);
    }

    public IntegerProperty invProperty() {
        return inv;
    }

    public int getMin() {
        return min.get();
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public IntegerProperty minProperty() {
        return min;
    }

    public int getMax() {
        return max.get();
    }

    public void setMax(int max) {
        this.max.set(max);
    }

    public IntegerProperty maxProperty() {
        return max;
    }


}
