import java.util.ArrayList;
import java.time.LocalDate;
import java.time.DateTimeException;

public class Admin extends User {

    // Stores all added products
    private final ArrayList<Product> records = new ArrayList<>();

    Admin() {
        super();

    }

    public void addSalesMan() {

        System.out.println("Enter the id: ");
        int id = sc.nextInt();

        System.out.println("Enter phone number: ");
        int phoneNumber = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter name: ");
        String name = sc.nextLine();

        System.out.println("Enter address: ");
        String add = sc.nextLine();

        // SalesMan object will be created later
    }

    public void addProduct() {

        System.out.print("Enter product name: ");
        String productName = sc.nextLine();

        System.out.print("Enter product ID: ");
        int id = sc.nextInt();

        System.out.print("Enter expiry year (e.g. 2026): ");
        int year = sc.nextInt();

        System.out.print("Enter expiry month (1-12): ");
        int month = sc.nextInt();

        System.out.print("Enter expiry day (1-31): ");
        int day = sc.nextInt();

        LocalDate expiryDate;
        try {
            expiryDate = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            System.out.println("Invalid date. Product not added.");
            return;
        }

        System.out.print("Enter quantity received: ");
        int quantity = sc.nextInt();

        System.out.print("Enter price per item: ");
        double price = sc.nextDouble();
        sc.nextLine();

        records.add(new Product(id, quantity, price, productName, expiryDate));

        System.out.println("Product added successfully.");
    }

    public void addOrder() {

        System.out.println("Enter Order ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter customer name: ");
        String name = sc.nextLine();

        System.out.println("Enter customer address: ");
        String address = sc.nextLine();

        System.out.println("Enter product ID: ");
        int pid = sc.nextInt();

        System.out.println("Enter quantity of product: ");
        int q = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter order date");

        System.out.print("Day: ");
        int d = sc.nextInt();

        System.out.print("Month: ");
        int m = sc.nextInt();

        System.out.print("Year: ");
        int y = sc.nextInt();

        LocalDate orderDate;
        try {
            orderDate = LocalDate.of(y, m, d);
        } catch (DateTimeException e) {
            System.out.println("Invalid date, order not executed");
            return;
        }

        System.out.println("The order was placed successfully.");
    }

    public void checkInventory(){

        
  if (records.isEmpty()) {
        System.out.println("Inventory is empty.");
        return;
    }

        System.out.println("The current Inventory is: ");
      for(Product p : records){
        System.out.println(p);
        System.out.println("-------------------------");
      }
    }

    public void mostSelling(){

    }

    
    public void mostProfitable(){

    }

    public void leastSelling(){

    }

    public void changeAdminPassword() {
        System.out.print("Enter old password: ");
        String oldPass = sc.nextLine();

        System.out.print("Enter new password: ");
        String newPass = sc.nextLine();

        changePassword(oldPass, newPass);
    }

    public void logout(){
       super.logout();
    }

    public void login(){
        System.out.println("=== login as an admin ===");
        System.out.println("Enter your password: ");
        String pass = sc.nextLine();
        super.login(pass);
    }

}