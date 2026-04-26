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

    public void changePrice(double newPrice) {
        if (newPrice < 0) {
            return;
        }
        this.price = newPrice;
    }

    public void increaseAvailableQuantity(int quantity) {
        if (quantity > 0) {
            this.availableQuantity += quantity;
        }
    }

    public boolean decreaseAvailableQuantity(int quantity) {
        if (quantity <= 0) {
            return false;
        }

        if (quantity > availableQuantity) {
            return false;
        }

        this.availableQuantity -= quantity;
        return true;
    }
}
