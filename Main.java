import java.util.Scanner;
public class Main {
    public static void main (String [] args){
         
        Scanner sc = new Scanner(System.in);
        User a= null;
        User o= null;

        System.out.println("1.Register");
        System.out.println("2. login");
        int choice1= sc.nextInt();
        int choice2;

       switch(choice1){

            case 1: System.out.println("1.Admin");
                    System.out.println("2.Owner");
                    System.out.println("3.Salesman");
                    choice2=sc.nextInt();
                     
                    if(choice2==1)
                        a=new Admin();
                    else if(choice2==2)
                        o=new Owner();

                    //add salesman later
               break;
            case 2:  System.out.println("1.Admin");
                     System.out.println("2.Owner");
                     System.out.println("3.Salesman");   
                     choice2=sc.nextInt();

                     if(choice2==1)
                        a.login("Password");
                    else if(choice2==2)
                        o.login("Password");

                    //add salesman later
                  break;  
        }

    }
}