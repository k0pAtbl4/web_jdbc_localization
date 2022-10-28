package jdbc.entity;

public class ProductInfo {
    private int orderNumber;
    private Product product;
    private int amount;

    public ProductInfo(int orderNumber, Product product, int amount) {
        this.orderNumber = orderNumber;
        this.product = product;
        this.amount = amount;
    }

    public ProductInfo() {
    }

    @Override
    public String toString() {
        return "\nid = " + orderNumber +
                ", \nproduct = " + product +
                ", \namount = " + amount +
                '}';
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
