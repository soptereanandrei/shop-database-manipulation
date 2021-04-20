package model;

public class Order {
    private String productName;
    private String clientName;
    private int quantity;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order() {}

    public Order(String productName, String clientName, int quantity)
    {
        this.productName = productName;
        this.clientName = clientName;
        this.quantity = quantity;
    }

    public String toString()
    {
        return "productName=" + productName + " clientName=" + clientName + " quantity=" + quantity;
    }
}
