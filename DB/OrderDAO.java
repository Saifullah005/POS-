package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import src.Order;

public class OrderDAO {

    public int createOrder(String orderDate, int adminId, double totalPrice, double totalProfit) {
        if (orderDate == null || orderDate.isBlank()) {
            return -1;
        }

        if (adminId <= 0) {
            return -1;
        }

        String sql = "INSERT INTO orders(order_date, placed_by_admin_id, total_price, total_profit) VALUES (?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, orderDate);
            pst.setInt(2, adminId);
            pst.setDouble(3, totalPrice);
            pst.setDouble(4, totalProfit);

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage());
        }

        return -1;
    }

    // LOCALDATE OVERLOAD

    public int createOrder(LocalDate orderDate, int adminId, double totalPrice, double totalProfit) {
        if (orderDate == null) {
            return -1;
        }

        return createOrder(orderDate.toString(), adminId, totalPrice, totalProfit);
    }

    public boolean addOrderItem(int orderId, int purchaseId, int productId, String productName, int quantity,
            double sellingPrice, double costPrice) {

        if (orderId <= 0) {
            return false;
        }

        if (productId <= 0) {
            return false;
        }

        if (quantity <= 0) {
            return false;
        }

        String sql = "INSERT INTO order_items(order_id, purchase_id, product_id, product_name, quantity, selling_price, cost_price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, orderId);
            pst.setInt(2, purchaseId);
            pst.setInt(3, productId);
            pst.setString(4, productName);
            pst.setInt(5, quantity);
            pst.setDouble(6, sellingPrice);
            pst.setDouble(7, costPrice);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage());
        }

        return false;
    }

    public boolean saveCart(
            int orderId,
            Order cart) {

        if (cart == null || cart.isEmpty()) {
            return false;
        }

        Order temp = cart.first;

        while (temp != null) {

            boolean success = addOrderItem(
                    orderId,
                    temp.getPurchaseId(),
                    temp.getProductId(),
                    temp.getProductName(),
                    temp.getQuantity(),
                    temp.getSellingPrice(),
                    temp.getCostPrice());

            if (!success) {
                return false;
            }

            temp = temp.next;
        }

        return true;
    }

    public boolean assignSalesman(
            int orderId,
            int salesmanId) {
        if (orderId <= 0 || salesmanId <= 0) {
            return false;
        }

        String sql = "UPDATE orders " +
                "SET assigned_salesman_id=? " +
                "WHERE order_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, salesmanId);
            pst.setInt(2, orderId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage());
        }

        return false;
    }

    public int getAssignedSalesman(int orderId) {

        if (orderId <= 0) {
            return 0;
        }

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT assigned_salesman_id " +
                    "FROM orders " +
                    "WHERE order_id=?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, orderId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                return rs.getInt(
                        "assigned_salesman_id");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    public boolean markDelivered(
            int orderId) {
        if (orderId <= 0) {
            return false;
        }

        String sql = "UPDATE orders " +
                "SET status='DELIVERED' " +
                "WHERE order_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, orderId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage());
        }

        return false;
    }

    public boolean deleteOrder(int orderId) {

        if (orderId <= 0) {
            return false;
        }

        try {

            Connection con = DBConnection.getConnection();

            con.setAutoCommit(false);

            String deleteItems = "DELETE FROM order_items WHERE order_id=?";

            PreparedStatement pst1 = con.prepareStatement(deleteItems);

            pst1.setInt(1, orderId);

            pst1.executeUpdate();

            String deleteOrder = "DELETE FROM orders WHERE order_id=?";

            PreparedStatement pst2 = con.prepareStatement(deleteOrder);

            pst2.setInt(1, orderId);

            int rows = pst2.executeUpdate();

            con.commit();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Integer> getPendingOrderIds(
            int salesmanId) {
        if (salesmanId <= 0) {
            return new ArrayList<>();
        }

        ArrayList<Integer> orders = new ArrayList<>();

        String sql = "SELECT order_id " +
                "FROM orders " +
                "WHERE assigned_salesman_id=? " +
                "AND status='PENDING' " +
                "ORDER BY order_id";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, salesmanId);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                orders.add(
                        rs.getInt(
                                "order_id"));
            }

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage());
        }

        return orders;
    }

    public ArrayList<Integer> getDeliveredOrderIds(
            int salesmanId) {

        ArrayList<Integer> orders = new ArrayList<>();

        String sql = "SELECT order_id " +
                "FROM orders " +
                "WHERE assigned_salesman_id=? " +
                "AND status='DELIVERED' " +
                "ORDER BY order_id";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, salesmanId);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                orders.add(
                        rs.getInt(
                                "order_id"));
            }

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage());
        }

        return orders;
    }

    public boolean orderExists(int orderId) {

        if (orderId <= 0) {
            return false;
        }

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT order_id " +
                    "FROM orders " +
                    "WHERE order_id=?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, orderId);

            ResultSet rs = pst.executeQuery();

            return rs.next();

        } catch (Exception e) {

            return false;
        }
    }
}