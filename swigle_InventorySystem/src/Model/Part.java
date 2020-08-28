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

    // Constructors
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

    // Getters & Setters
    public int getId() {
        return id;
    }
    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public double getPrice() {
        return price.get();
    }
    public void setPrice(double price) {
        this.price.set(price);
    }
    public int getInv() {
        return inv.get();
    }
    public void setInv(int inv) {
        this.inv.set(inv);
    }
    public int getMin() {
        return min.get();
    }
    public void setMin(int min) {
        this.min.set(min);
    }
    public int getMax() {
        return max.get();
    }
    public void setMax(int max) {
        this.max.set(max);
    }
}
