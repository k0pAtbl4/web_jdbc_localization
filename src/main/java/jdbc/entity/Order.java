package jdbc.entity;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private int id;
    private List<ProductInfo> productInfo;
    private LocalDate receiptDate;

    public Order() {
    }

    public Order(int id, LocalDate receiptDate) {
        this.id = id;
        this.receiptDate = receiptDate;
    }

    public Order(int id, List<ProductInfo> productInfo, LocalDate receiptDate) {
        this.id = id;
        this.productInfo = productInfo;
        this.receiptDate = receiptDate;
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", \nproducts info = " + productInfo +
                ", \nreceiptDate = " + receiptDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProductInfo> getProductInfo() {
        return productInfo;
    }

    public void setItemsOnOrder(List<ProductInfo> productInfo) {
        this.productInfo = productInfo;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }
}
