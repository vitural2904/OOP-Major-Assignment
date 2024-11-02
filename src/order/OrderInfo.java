package order;

public class OrderInfo {

    private String productID;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;

    public OrderInfo(String productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;

        Product newItem = new Product(productID);

        this.productName = newItem.getProductName(); 
        // the getProductName is invalid at the moment, Son hasn't finish it yet
        this.unitPrice = newItem.getSellingPrice();
        this.totalPrice = this.unitPrice * quantity;
    }

    // Getter methods
    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
