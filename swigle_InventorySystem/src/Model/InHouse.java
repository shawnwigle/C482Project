package Model;

public class InHouse extends Part {
    // Field
    private int machineId;

    // Constructor
    public InHouse(String name, double price, int stock, int min, int max, int machineId) {
        super(name, price, stock, min, max);
        this.machineId = machineId;
    }

    // Getter & Setter
    public int getMachineId() {
        return machineId;
    }
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
