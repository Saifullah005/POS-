package src;
import java.util.ArrayList;

class Salesman extends User {

    public static int commission = 5;

    // orrect storage for assigned orders
    private ArrayList<Order> assignedOrders = new ArrayList<>();

    private int order_delivered = 0;

    public Salesman() {
        super();
    }

    public Salesman(int userid, String name, String password, String role) {
        super(userid, name, password, role);
    }

    //Assign FULL linked list
    public void assignOrder(Order o) {
        assignedOrders.add(o);
        System.out.println("Order assigned to salesman.");
    }

    //Calculate salary based on all assigned orders
    public void calculate_salary() {
        int totalQuantity = 0;

        for (Order order : assignedOrders) {

            Order temp = order.first;

            while (temp != null) {
                totalQuantity += temp.getQuantity();
                temp = temp.next;
            }
        }

        int salary = totalQuantity * commission;

        System.out.println("Total salary is: " + salary);
    }

    // Total orders assigned
    public void total_orders() {
        System.out.println("Total orders to deliver: " + assignedOrders.size());
    }

    // Orders delivered
    public void check_deliver() {
        System.out.println("Total orders delivered: " + order_delivered);
    }

    // Update delivered orders
    public void updateorder() {
        if (assignedOrders.isEmpty()) {
            System.out.println("No orders to deliver.");
            return;
        }

        System.out.println("Enter number of orders delivered:");
        int delivered = sc.nextInt();

        if (delivered > assignedOrders.size()) {
            System.out.println("Invalid number.");
            return;
        }

        // remove delivered orders
        for (int i = 0; i < delivered; i++) {
            assignedOrders.remove(0);
        }

        order_delivered += delivered;

        System.out.println("Orders updated successfully.");
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
        System.out.println("=== login as a Salesman ===");
        System.out.println("Enter your password: ");
        String pass = sc.nextLine();
        super.login(pass);
    }
}