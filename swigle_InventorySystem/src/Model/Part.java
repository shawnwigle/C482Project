package Model;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Part {
    // Fields
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    // Constructor
    public Part(String name, double price, int stock, int min, int max) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.price = price;
        this.stock = stock;
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


}
