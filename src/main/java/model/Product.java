package model;

public class Product {
    private String name;
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product() { }

    public Product(String name, int quantity)
    {
        this.name = name;
        this.quantity = quantity;
    }

    public String toString()
    {
        return "name=" + name + " quantity=" + quantity;
    }
}
