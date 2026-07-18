package src;
import java.util.Scanner;
public class Main {
    public static void main (String [] args){
         
        Scanner sc = new Scanner(System.in);
        User adminUser= null;
        User ownUser= null;
        User salesmanUser= null;

        System.out.println("1.Register");
        System.out.println("2. login");
        int choice1= sc.nextInt();
       int choice2=0;

       switch(choice1){

            case 1: System.out.println("1.Admin");
                    System.out.println("2.Owner");
                    System.out.println("3.Salesman");
                    choice2=sc.nextInt();
                     
                    if(choice2==1)
                        adminUser=new Admin();
                    else if(choice2==2)
                        ownUser= new Owner();
                    else
                        salesmanUser=new Salesman();
               break;
            case 2:  System.out.println("1.Admin");
                     System.out.println("2.Owner");
                     System.out.println("3.Salesman");   
                     choice2=sc.nextInt();

                     if(choice2==1)
                        adminUser.login("Password");
                     else if(choice2==2)
                        ownUser.login("Password");
                     else
                        salesmanUser.login("Password");

                  break;  
        }

        if(choice2 == 1){
            System.out.println("ADMIN PANNEL");
            System.out.println("1.Add Product");
            System.out.println("2.Add  Salesman");
            System.out.println("3.Add Order");
            System.out.println("4.update Quantity");
            System.out.println("5.Check inventry");
            System.out.println("6.check montlhy most selling product");
            System.out.println("7.Check montlhy most profitable product");
            System.out.println("8.check daily most selling product");
            System.out.println("9.Check daliy most profitable product");
            System.out.println("10.Change password");
            System.out.println("11.Logout");


        }
        if(choice2==2){
            System.out.println("OWNER PANNEL");
            System.out.println("1.Daily sales record");
            System.out.println("2.Total Orders");
            System.out.println("3.Total profit");
            System.out.println("4.Check inventry");
            System.out.println("5.check montlhy most selling product");
            System.out.println("6.Check montlhy most profitable product");
            System.out.println("7.check daily most selling product");
            System.out.println("8.Check daliy most profitable product");
            System.out.println("9.Change password");
            System.out.println("10.Logout");
        }
        if(choice2==3){
            System.out.println("SALES MAN PANNEL");
            System.out.println("1.Check pending orders");
            System.out.println("2.check deliver orders");
            System.out.println("3.Update orders");
            System.out.println("4.Check salary");
            System.out.println("5.Change password");
            System.out.println("6.Logout");
        }

    }
}