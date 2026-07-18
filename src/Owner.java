package src;
import java.util.Scanner;

public class Owner extends User{
    Scanner sc = new Scanner(System.in);
    int tprofit=0,tsale=0;
    Owner(){
        super();
    }
    Owner(int id , String name ,String pass){
     super(id, name , pass , "Owner");
     
    }

    Order o1= new Order();
    Admin a1= new Admin();
    
    String dailySales(){
        System.out.println("DAILY RECORD IS:");
        System.out.println();
        for(int i =0;i<o1.record.size();i++){
            tprofit+=o1.record.get(i).t_order_profit;
            tsale+=o1.record.get(i).t_order_price;
        }
        System.out.println();
        System.out.println();
    
        return "Total number of orders are: "+o1.record.size() + 
                "\nTotal profit is:"+tprofit + 
                "\nTotal sale is:"+tsale;
    }
    
    
    void MonthlyPopular(){
        a1.mostProfitableByMonth();

    }
    void monthlymostsellig(){
         a1.mostSellingByMonth();

    }
     void dailyPopular(){
         a1.DailyProfitable();
    
    }
    void dailymostselling(){
        a1.DailymostSelling(); 
    }

   void inventory(){
       a1.checkInventory();
   }
    
    
    void totalprofit(){
          System.out.println("Total profit is:"+tprofit);
          System.out.println("Total sale is:"+tsale);

    }
    
    
    void totalorders(){
         System.out.println("Total number of orders are: "+o1.record.size());
    
    }
    
    
   
    
    
    void monthlymostselling(){
        
      a1.mostSellingByMonth(); 

    }
    void dailylymostselling(){
        
      a1.DailymostSelling(); 

    }
    
    void dailymostprofitable(){
    
    a1.DailyProfitable();
    
    }
    
    void monthlymostprofitable(){
    
       a1.mostProfitableByMonth();
    
    }
    
     public void changeOwnerPassword() {
        System.out.print("Enter old password: ");
        String oldPass = sc.nextLine();

        System.out.print("Enter new password: ");
        String newPass = sc.nextLine();

        changePassword(oldPass, newPass);
    }

    public void logout() {
        super.logout();
    }

    public void login() {
        System.out.println("=== login as an Owner ===");
        System.out.println("Enter your password: ");
        String pass = sc.nextLine();
        super.login(pass);
    }
    
    
}