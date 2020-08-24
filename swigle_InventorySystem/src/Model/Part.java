package Model;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Part {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private String name;
    private double price;
    // Fields
    private final int id;
    private int min;
    private int max;
    private int inv;

    // Constructor
    public Part(String name, double price, int inv, int min, int max) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.min = min;
        this.max = max;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //    public void setId(int id) {
//        this.id = id;
//    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv = inv;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }


}
