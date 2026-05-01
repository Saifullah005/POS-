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

    Order next,previous,first; 
    ArrayList <Order> record = new ArrayList<>();
    Scanner sc = new Scanner (System.in);

    public Order(){

    }
    public Order(int purchaseId, int productId, String productName, int quantity, double sellingPrice, double costPrice, LocalDate date) {

        this.purchaseId = purchaseId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.costPrice = costPrice;
        this.date = date;
    }

     public void addproduct(){
        char ch ='y';
        first=null;
        while (ch=='y'){
               
               Order current = new Order();
               System.out.println("Enetr the purchase id: ");
               current.productId=sc.nextInt();
               System.out.println("Enter the product id: ");
               current.productId=sc.nextInt();
               System.out.println("Enter the product name:");
               current.productName=sc.nextLine();
               sc.nextLine();
               System.out.println("Enter the quantity: ");
               current.quantity=sc.nextInt();
               System.out.println("Enter the selling price:");
               current.sellingPrice=sc.nextDouble();
               System.out.println("Enter the Cost Price: ");
               current.costPrice=sc.nextDouble();
               

               if(first==null){
                    first=previous=current;

               }
               else{
                     previous.next=current;
                     previous=previous.next;
               }
        }
           
             System.out.println("Want to buy more products[y/n]:");
             ch=sc.next().charAt(0);
         
             if(ch!='y'){
                record.add(first);
             }

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
