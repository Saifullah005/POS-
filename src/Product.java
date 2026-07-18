package src;
import java.time.LocalDate;

public class Product {
    // Threshold for low stock:
    private static final int THRESHOLD = 5;

    private int id, quantity;
    private double sellingPrice;
    private double costPrice;
    private String name;
    private LocalDate expiryDate;

    public Product(int id, int quantity, double sellingPrice,
            double costPrice, String name, LocalDate expDate) {
        this.id = id;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.costPrice = costPrice;
        this.name = name;
        this.expiryDate = expDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public double getSalePrice() {
        return sellingPrice;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public boolean soldFromStock(int q) {

        if (q <= 0) {
            return false;
        }

        if (this.quantity >= q) {
            this.quantity -= q;
            return true;
        }
        return false;
    }

    public boolean reStock(int q) {
        if (q <= 0) {
            return false;
        }
        quantity += q;
        return true;
    }

    public boolean lowStock() {
        if (quantity <= THRESHOLD) {
            return true;
        }
        return false;
    }

    public boolean checkExpiry() {
        LocalDate current = LocalDate.now();
        if (current.isAfter(expiryDate)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Product ID: " + id
                + "\nProduct Name: " + name +
                "\nQuantity: " + quantity +
                "\nCostPrice: " + costPrice +
                "\nSellingPrice: " + sellingPrice +
                "\nDate: " + expiryDate;
    }
}
