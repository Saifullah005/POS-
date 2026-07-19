package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import DB.OrderDAO;

public class Salesman extends User {

    public static final int COMMISSION = 5;

    private OrderDAO orderDAO;
    private Queue<Integer> assignedOrders;

    public Salesman() {
        super();
        orderDAO = new OrderDAO();
        assignedOrders = new LinkedList<>();
    }

    public Salesman(int userid, String name, String password, String role) {
        super(userid, name, password, role);
        orderDAO = new OrderDAO();
        assignedOrders = new LinkedList<>();
    }

    public void loadAssignedOrders() {

        assignedOrders.clear();

        ArrayList<Integer> orders = orderDAO.getPendingOrderIds(getId());

        for (Integer orderId : orders) {
            assignedOrders.offer(orderId);
        }
    }

    public Integer viewNextOrder() {
        return assignedOrders.peek();
    }

    public boolean deliverNextOrder() {

        if (assignedOrders.isEmpty()) {
            return false;
        }

        int orderId = assignedOrders.peek();

        if (orderDAO.markDelivered(orderId)) {
            assignedOrders.poll();
            return true;
        }

        return false;
    }

    public boolean hasPendingOrders() {
        return !assignedOrders.isEmpty();
    }

    public ArrayList<Integer> viewAssignedOrders() {
        return new ArrayList<>(assignedOrders);
    }

    public ArrayList<Integer> viewDeliveredOrders() {
        return orderDAO.getDeliveredOrderIds(getId());
    }

    public int pendingOrdersCount() {
        return assignedOrders.size();
    }

    public String totalOrders() {
        return "Pending Orders: " + assignedOrders.size();
    }

    public boolean markOrderDelivered(int orderId) {
        return orderDAO.markDelivered(orderId);
    }

    public String calculateSalary() {
        return "Salary calculation pending implementation";
    }

    public void changeSalesmanPassword() {

        System.out.print("Enter old password: ");
        String oldPass = sc.nextLine();

        System.out.print("Enter new password: ");
        String newPass = sc.nextLine();

        changePassword(oldPass, newPass);
    }

    @Override
    public void logout() {
        super.logout();
    }

    public void login() {

        System.out.println("=== Login As Salesman ===");
        System.out.print("Enter Password: ");

        String pass = sc.nextLine();

        if (super.login(pass)) {
            loadAssignedOrders();
        }
    }
}