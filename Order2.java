import java.time.LocalDate;

public class Order2 {

    private int purchaseId;
    private int productId;
    private String productName;
    private int quantity;
    private double sellingPrice;
    private double costPrice;
    private LocalDate date;

    public Order2(int purchaseId, int productId, String productName, int quantity, double sellingPrice, double costPrice, LocalDate date) {

        this.purchaseId = purchaseId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.costPrice = costPrice;
        this.date = date;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTotal() {
        return quantity * sellingPrice;
    }

    public double getProfit() {
        return (sellingPrice - costPrice) * quantity;
    }
}
