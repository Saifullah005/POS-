import java.util.ArrayList;

public class Salesman extends User {

    public static final int commission = 5;
    Order2 o1 = new Order2();
    public int tOrder=o1.record.size();
    public int orderDeliver;

    public Salesman() {
        super();
    }

    public Salesman(int userid, String name, String password, String role) {
        super(userid, name, password, role);
    }
    
    public void claculates_salary(){
      int  quantity = 0;
      int salary = 0;
      for(int i=0;i<o1.record.size();i++){
          Order2 temp=o1.record.get(i).first;
          while(temp!=null){
             quantity+=temp.getQuantity();
             temp=temp.next;

          }

           
      }
      salary = quantity * this.commission;
      System.out.println("Total salary is:"+salary);

    }

    public void total_order(){
       System.out.println("Total order need to deliver are:"+this.tOrder);

    }
    public void check_deliver(){
       System.out.println("Total number od order you deliver are:"+this.orderDeliver);

    }
    public void updateorder(){
        System.out.println("Enter the number of order you deliver:");
        int quantity=sc.nextInt();
        tOrder-=quantity;
        orderDeliver+=quantity;
    }
     public void changeSalesmanPassword() {
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
        System.out.println("=== login as an Salesman ===");
        System.out.println("Enter your password: ");
        String pass = sc.nextLine();
        super.login(pass);
    }
}