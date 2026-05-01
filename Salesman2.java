import java.util.ArrayList;

public class Salesman2 extends User {

    private static final int commission = 5;

    private Admin_2 admin;


    private ArrayList<Order> assignedOrders;

    private int userId , phoneNumber ;
    private String name , address;

    private int totalAssignedOrders;
    private int deliveredOrders;
    private int deliveredQuantity;

    
    public Salesman2(Admin_2 admin, int id , int phoneNumber, String name , String address) {
        super();
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.userId = id;
        this.admin = admin;
        this.assignedOrders = new ArrayList<>();
        this.totalAssignedOrders = 0;
        this.deliveredOrders = 0;
        this.deliveredQuantity = 0;

    }


    public void assignOrder(Order order) {
        if (order == null) {
            System.out.println("Invalid order.");
            return;
        }

        assignedOrders.add(order);
        totalAssignedOrders++;
        System.out.println("Order assigned successfully.");
    }

   
    public void deliverNextOrder() {
        if (assignedOrders.isEmpty()) {
            System.out.println("No orders to deliver.");
            return;
        }

        Order order = assignedOrders.remove(0);

        deliveredOrders++;
        deliveredQuantity += order.getQuantity();

        System.out.println(
                "Delivered order for Product ID: "
                        + order.getProductId()
                        + " | Quantity: "
                        + order.getQuantity()
        );
    }

    
    public int getPendingDeliveries() {
        return totalAssignedOrders - deliveredOrders;
    }

    
    public int calculateSalary() {
        return deliveredQuantity * commission;
    }

    
    public void viewPerformance() {
        System.out.println("=== Salesman Performance ===");
        System.out.println("Total orders assigned : " + totalAssignedOrders);
        System.out.println("Orders delivered      : " + deliveredOrders);
        System.out.println("Pending deliveries    : " + getPendingDeliveries());
        System.out.println("Total quantity delivered : " + deliveredQuantity);
        System.out.println("Current salary earned : " + calculateSalary());
    }

    
    public void changeSalesmanPassword() {
        System.out.print("Enter old password: ");
        String oldPass = sc.nextLine();

        System.out.print("Enter new password: ");
        String newPass = sc.nextLine();

        changePassword(oldPass, newPass);
    }

    
    public void login() {
        System.out.println("=== Login as Salesman ===");
        System.out.print("Enter your password: ");
        super.login(sc.nextLine());
    }

    public void logout() {
        super.logout();
    }
}