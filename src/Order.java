package src;

import java.time.LocalDate;
import java.util.Scanner;

public class Order {

    private int purchaseId;
    private int productId;
    private String productName;
    private int quantity;
    private double sellingPrice;
    private double costPrice;
    private LocalDate date;

    public Order next;
    public Order first;
    private Order previous;

    Scanner sc = new Scanner(System.in);

    public Order() {

    }

    public void addProduct() {
        char ch = 'y';
        first = null;
        previous = null;

        while(ch == 'y' || ch == 'Y'){
            Order current = new Order();

            System.out.print("Enter Purchase ID: ");
            current.purchaseId = sc.nextInt();

            System.out.print("Enter Product ID: ");
            current.productId = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter Product Name: ");
            current.productName = sc.nextLine();

            System.out.print("Enter Quantity: ");
            current.quantity = sc.nextInt();

            System.out.print("Enter Selling Price: ");
            current.sellingPrice = sc.nextDouble();

            System.out.print("Enter Cost Price: ");
            current.costPrice = sc.nextDouble();

            if (first == null) {

                first = current;
                previous = current;

            } else {

                previous.next = current;
                previous = current;
            }

            System.out.println("Do you want to add more[y/n]");
            ch = sc.next().charAt(0);
        }
    }

    public double getTotalOrderPrice() {

        double total = 0;

        Order temp = first;

        while (temp != null) {

            total += temp.getTotal();

            temp = temp.next;
        }

        return total;
    }

    public double getTotalOrderProfit() {

        double totalProfit = 0;

        Order temp = first;

        while (temp != null) {

            totalProfit += temp.getProfit();

            temp = temp.next;
        }

        return totalProfit;
    }

    public String displayCart() {

        Order temp = first;

        StringBuilder sb = new StringBuilder();

        while (temp != null) {

            sb.append("----------------------\n");
            sb.append("Product ID : ")
                    .append(temp.productId)
                    .append("\n");

            sb.append("Name       : ")
                    .append(temp.productName)
                    .append("\n");

            sb.append("Quantity   : ")
                    .append(temp.quantity)
                    .append("\n");

            sb.append("Price      : ")
                    .append(temp.sellingPrice)
                    .append("\n");

            temp = temp.next;
        }

        sb.append("----------------------\n");
        sb.append("Total Price : ")
                .append(getTotalOrderPrice());

        return sb.toString();
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

    public double getSellingPrice() {
        return sellingPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotal() {

        return quantity * sellingPrice;
    }
    public double getProfit() {

        return (sellingPrice - costPrice)
                * quantity;
    }
 
    public void clearCart() {
        first = null;
        previous = null;
    }
    // ==================================================================
    // ADDITIVE FOR GUI  -  nothing above or below this block is changed
    // ------------------------------------------------------------------
    // The existing addProduct() builds cart nodes by reading a Scanner,
    // and the fields are private with no setters, so a JavaFX screen has
    // no way to construct a node. These two members expose the SAME
    // linked-list behaviour without console input:
    //
    //   - a parameterised constructor (fills one node)
    //   - addToCart(node), which uses the identical first/previous
    //     linking logic already written inside addProduct()
    //
    // No existing method is altered. The console app still works.
    // ==================================================================

    public Order(int purchaseId,
                 int productId,
                 String productName,
                 int quantity,
                 double sellingPrice,
                 double costPrice) {

        this.purchaseId   = purchaseId;
        this.productId    = productId;
        this.productName  = productName;
        this.quantity     = quantity;
        this.sellingPrice = sellingPrice;
        this.costPrice    = costPrice;
    }

    public void addToCart(Order item) {

        if (first == null) {
            first    = item;
            previous = item;

        } else {

            previous.next = item;
            previous      = item;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {

        return "Product ID : " + productId
                + "\nName : " + productName
                + "\nQuantity : " + quantity
                + "\nSelling Price : " + sellingPrice
                + "\nCost Price : " + costPrice;
    }

}
