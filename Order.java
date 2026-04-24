import java.util.Scanner;
public class Order {
    
    int purchaseid;
    int productid;
    String productname;
    int quantity;
    float sellingprice;
    float costprice;
    float total=0;
    float profit=0;
    String date;
    
    
    Order next, previous, first;
    
    
    void addproduct(){
        
        Scanner sc = new Scanner(System.in);
        
        char ch='y';
        while(ch=='y'){
        
            Order current = new Order();
            System.out.println("Enter purchase id:");
            current.purchaseid=sc.nextInt();
            System.out.println("Enter product id: ");
            current.productid=sc.nextInt();
            System.out.println("Enter product name:");
            current.productname=sc.nextLine();
            System.out.println("Enter Quantity sold : ");
            current.quantity=sc.nextInt();
            System.out.println("Enter Selling price per unit:");
            current.sellingprice=sc.nextFloat();
            System.out.println("Enter cost price per unit:");
            current.costprice=sc.nextFloat();
            
            current.total=current.quantity*current.sellingprice;
            current.profit=(current.sellingprice-current.costprice)*current.quantity;
            
            System.out.println("ENter the current date:");
            current.date=sc.nextLine();
            
            
            if(first==null){
               first=previous=current;
            
            }
            else{

                previous.next=current;
                previous=current;
            
            
            }
            
            System.out.println("Want to  buy more product[y/n]:");
            ch=sc.next().charAt(0);
        
        
        }
    
    sc.close();
    
    }
    
    void totalsale(){
    
        Order temp=first;
        int totalsale=0;
        while(temp!=null){
            
            totalsale+=temp.total;
            temp=temp.next;
        
        }
        System.out.println("Total Sale is : "+totalsale);
    
    
    }
    
    void profit(){
        
          Order temp=first;
        int totalprofit=0;
        while(temp!=null){
            
            totalprofit+=temp.profit;
            temp=temp.next;
        
        }
        System.out.println("Total profit is : "+totalprofit);
    }
    
    
}