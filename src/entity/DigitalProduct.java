package entity;

public class DigitalProduct extends Product {
    private double sizeMb;

    public DigitalProduct(String id, String name, double price, double sizeMb) {
        super(id, name, price);
        this.sizeMb = sizeMb;
    }

    public double getSizeMb() {
        return sizeMb;
    }

    public void setSizeMb(double sizeMb) {
        this.sizeMb = sizeMb;
    }

    @Override
    public void displayInfo() {
        System.out.printf("[Digital ] ID: %s | Name: %s | Price: %.2f | Size: %.2f MB%n",
                getId(), getName(), getPrice(), sizeMb);
    }
}

