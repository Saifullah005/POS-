package src;
import java.time.LocalDate;
import java.util.*;

public class Order {

    private int purchaseId;
    private int productId;
    private String productName;
    private int quantity;
    private double sellingPrice;
    private double costPrice;
    private LocalDate date;
    double t_order_profit=0;
    double t_order_price=0;
    double distance;
    
    Order next,previous,first; 
    ArrayList <Order> record = new ArrayList<>();
    Scanner sc = new Scanner (System.in);

    public Order(){

    }
  

     public void addproduct() {
        char ch = 'y';
        first = null;

        while (ch == 'y') {

            Order current = new Order();

            System.out.println("Enter the purchase id: ");
            current.purchaseId = sc.nextInt();

            System.out.println("Enter the product id: ");
            current.productId = sc.nextInt();

            sc.nextLine();

            System.out.println("Enter the product name:");
            current.productName = sc.nextLine();

            System.out.println("Enter the quantity: ");
            current.quantity = sc.nextInt();

            System.out.println("Enter the selling price:");
            current.sellingPrice = sc.nextDouble();

            System.out.println("Enter the Cost Price: ");
            current.costPrice = sc.nextDouble();

            if (first == null) {
                first = previous = current;
            } else {
                previous.next = current;
                previous = current;
            }

            System.out.println("Want to buy more products[y/n]:");
            ch = sc.next().charAt(0);
        }
        record.add(first);
     }

     public void addProduct(int productId, int purchaseId, String name, int quantity, double SP, double CP) {
        Order current = new Order();
        current.productId = productId;
        current.purchaseId = purchaseId;
        current.productName = name;
        current.quantity = quantity;
        current.sellingPrice = SP;
        current.costPrice = CP;

        if (first == null) {
            first = previous = current;
        } else {
            previous.next = current;
            previous = current;
        }

        record.add(current);
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

    public void setDate(LocalDate date){
        this.date = date;
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
   
    public void t_order_price(){
       
        Order temp=first;
        while(temp!=null){
            t_order_price+=temp.getTotal();
            temp=temp.next;
        }
        System.out.println("Total Price:"+t_order_price);

    }

    public void t_order_profit(){
         
        Order temp=first;
        while(temp!=null){
            t_order_profit+=temp.getProfit();
            temp=temp.next;
        }
        System.out.println("Total Profit:"+t_order_profit);

    }
  
}