package src;
public class ProductSummary {
    private int productId;
    private int totalQuantity;
    private double totalProfit;

    public ProductSummary(int productId, int quantity, double profit) {
        this.productId = productId;
        this.totalQuantity = quantity;
        this.totalProfit = profit;
    }

    public int getProductId() {
        return productId;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void addQuantity(int q) {
        totalQuantity += q;
    }

    public void addProfit(double p) {
        totalProfit += p;
    }
}