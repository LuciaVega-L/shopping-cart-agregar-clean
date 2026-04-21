package isi.shoppingCart.entities;

public class Product {
    private int id;
    private String name;
    private double price;
    private int availableQuantity;

    public Product(int id, String name, double price, int availableQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public boolean hasAvailableQuantity() {
        return availableQuantity > 0;
    }

    public void decreaseAvailableQuantity() {
        if (availableQuantity > 0) {
            availableQuantity = availableQuantity - 1;
        }
    }
}
