package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Outsourced extends Part{
    // Field
    private final StringProperty companyName;

    // Constructors
    public Outsourced() {
        super();
        this.companyName = new SimpleStringProperty();
    }
    public Outsourced(int id) {
        super(id);
        this.companyName = new SimpleStringProperty();
    }

    // Getters & Setters
    public String getCompanyName() {
        return this.companyName.get();
    }
    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

}
