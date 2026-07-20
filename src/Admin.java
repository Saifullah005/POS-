package src;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

import DB.OrderDAO;
import DB.ProductDAO;
import DB.ReportDAO;
import DB.UserDAO;

public class Admin extends User {

        private UserDAO userDAO;
        private ProductDAO productDAO;
        private OrderDAO orderDAO;
        private ReportDAO reportDAO;

        public Admin() {

                super();

                userDAO = new UserDAO();
                productDAO = new ProductDAO();
                orderDAO = new OrderDAO();
                reportDAO = new ReportDAO();
        }

        public Admin(
                        int id,
                        String name,
                        String password) {

                super(
                                id,
                                name,
                                password,
                                "ADMIN");

                userDAO = new UserDAO();
                productDAO = new ProductDAO();
                orderDAO = new OrderDAO();
                reportDAO = new ReportDAO();
        }

        public void addSalesMan() {

                System.out.print("Enter name: ");
                String name = sc.nextLine();

                System.out.print("Enter password: ");
                String pass = sc.nextLine();

                boolean success = userDAO.addUser(
                                name,
                                pass,
                                "SALESMAN");

                if (success) {

                        System.out.println(
                                        "Salesman added successfully.");
                } else {

                        System.out.println(
                                        "Failed to add salesman.");
                }
        }

        public void addProduct() {

                System.out.print(
                                "Enter product name: ");

                String productName = sc.nextLine();

                System.out.print(
                                "Enter expiry year: ");

                int year = sc.nextInt();

                System.out.print(
                                "Enter expiry month: ");

                int month = sc.nextInt();

                System.out.print(
                                "Enter expiry day: ");

                int day = sc.nextInt();

                LocalDate expiryDate;

                try {

                        expiryDate = LocalDate.of(
                                        year,
                                        month,
                                        day);

                } catch (DateTimeException e) {

                        System.out.println(
                                        "Invalid date.");

                        return;
                }

                System.out.print(
                                "Enter quantity: ");

                int quantity = sc.nextInt();

                System.out.print(
                                "Enter cost price: ");

                double costPrice = sc.nextDouble();

                System.out.print(
                                "Enter sale price: ");

                double salePrice = sc.nextDouble();

                sc.nextLine();

                Product product = new Product(
                                0,
                                quantity,
                                salePrice,
                                costPrice,
                                productName,
                                expiryDate);

                boolean success = productDAO.addProduct(
                                product);

                if (success) {

                        System.out.println(
                                        "Product added successfully.");
                } else {

                        System.out.println(
                                        "Failed to add product.");
                }
        }

        public void addOrder() {

                System.out.println(
                                "\n===== ADD PRODUCTS TO CART =====");

                Order cart = new Order();

                cart.addProduct();

                System.out.println(
                                "\n===== CART DETAILS =====");

                System.out.println(
                                cart.displayCart());

                double totalPrice = cart.getTotalOrderPrice();

                double totalProfit = cart.getTotalOrderProfit();

                int orderId = orderDAO.createOrder(
                                LocalDate.now(),
                                getId(),
                                totalPrice,
                                totalProfit);

                if (orderId == -1) {

                        System.out.println(
                                        "Failed to create order.");

                        return;
                }

                boolean success = orderDAO.saveCart(
                                orderId,
                                cart);

                if (success) {

                        System.out.println(
                                        "Order saved successfully.");
                } else {

                        System.out.println(
                                        "Failed to save order items.");
                }

                cart.clearCart();
        }

        public boolean assignOrderToSalesman(int orderId, int salesmanId) {

                return orderDAO.assignSalesman(orderId, salesmanId);

        }

        public void assignOrderToSalesman() {

                System.out.print("Enter Order ID: ");
                int orderId = sc.nextInt();

                System.out.print("Enter Salesman ID: ");
                int salesmanId = sc.nextInt();

                sc.nextLine();

                boolean success = assignOrderToSalesman(orderId, salesmanId);
                if (success) {
                        System.out.println("Order assigned successfully.");
                } else {
                        System.out.println("Failed to assign order.");
                }
        }

        public ArrayList<Product> checkInventory() {
                return productDAO.getAllProducts();
        }

        public String totalOrders() {

                return reportDAO.totalOrders();
        }

        public String totalProfit() {

                return reportDAO.totalProfit();
        }

        public String totalSales() {

                return reportDAO.totalSales();
        }

        public String todaySales() {

                return reportDAO.todaySales();
        }

        public String todayProfit() {

                return reportDAO.todayProfit();
        }

        public String mostSellingProduct() {

                return reportDAO.mostSellingProduct();
        }

        public String leastSellingProduct() {

                return reportDAO.leastSellingProduct();
        }

        public String mostProfitableProduct() {

                return reportDAO.mostProfitableProduct();
        }

        public String leastProfitableProduct() {

                return reportDAO.leastProfitableProduct();
        }

        public void deleteProduct(
                        int productId) {

                boolean success = productDAO.removeProduct(
                                productId);

                if (success) {

                        System.out.println(
                                        "Product deleted.");
                } else {

                        System.out.println(
                                        "Failed to delete product.");
                }
        }

        public void deleteOrder(
                        int orderId) {

                boolean success = orderDAO.deleteOrder(
                                orderId);

                if (success) {

                        System.out.println(
                                        "Order deleted.");
                } else {

                        System.out.println(
                                        "Failed to delete order.");
                }
        }

        public void deleteUser(
                        int userId) {

                boolean success = userDAO.deleteUser(
                                userId);

                if (success) {

                        System.out.println(
                                        "User deleted.");
                } else {

                        System.out.println(
                                        "Failed to delete user.");
                }
        }

        public void changeAdminPassword() {

                System.out.print(
                                "Enter old password: ");

                String oldPass = sc.nextLine();

                System.out.print(
                                "Enter new password: ");

                String newPass = sc.nextLine();

                changePassword(
                                oldPass,
                                newPass);
        }

        @Override
        public void logout() {

                super.logout();
        }

        public void login() {

                System.out.println(
                                "=== login as admin ===");

                System.out.print(
                                "Enter password: ");

                String pass = sc.nextLine();

                super.login(pass);
        }
}