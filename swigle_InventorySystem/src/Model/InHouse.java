package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InHouse extends Part {
    // Field
    private final IntegerProperty machineId;

    // Constructor
    public InHouse() {
        super();
        this.machineId = new SimpleIntegerProperty();
    }
    public InHouse(int id) {
        super(id);
        this.machineId = new SimpleIntegerProperty();
    }

    // Getter & Setter
    public int getMachineId() {
        return this.machineId.get();
    }
    public void setMachineId(int machineId) {
        this.machineId.set(machineId);
    }
}
