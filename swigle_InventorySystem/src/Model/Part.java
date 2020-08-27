package Model;

import javafx.beans.property.*;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Part {
    // Fields
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private final int id;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inv;
    private final IntegerProperty min;
    private final IntegerProperty max;

    // Constructor
    public Part() {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.inv = new SimpleIntegerProperty();
        this.min = new SimpleIntegerProperty();
        this.max = new SimpleIntegerProperty();
    }
    public Part(int id) {
        this.id = id;
        this.name = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.inv = new SimpleIntegerProperty();
        this.min = new SimpleIntegerProperty();
        this.max = new SimpleIntegerProperty();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getInv() {
        return inv.get();
    }

    public IntegerProperty invProperty() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv.set(inv);
    }

    public int getMin() {
        return min.get();
    }

    public IntegerProperty minProperty() {
        return min;
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public int getMax() {
        return max.get();
    }

    public IntegerProperty maxProperty() {
        return max;
    }

    public void setMax(int max) {
        this.max.set(max);
    }
}
