package src;

import java.util.ArrayList;
import java.util.Scanner;

import DB.UserDAO;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();

        System.out.println("1. Register");
        System.out.println("2. Login");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {

            System.out.println("1. Admin");
            System.out.println("2. Owner");
            System.out.println("3. Salesman");

            int roleChoice = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            String role = "";

            if (roleChoice == 1) {
                role = "ADMIN";
            } else if (roleChoice == 2) {
                role = "OWNER";
            } else {
                role = "SALESMAN";
            }

            if (userDAO.addUser(name, password, role)) {
                System.out.println("Registration Successful");
            } else {
                System.out.println("Registration Failed");
            }
        }

        System.out.print("Enter User ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (!userDAO.loginUser(id, password)) {

            System.out.println("Invalid Login");

            sc.close();
            return;
        }

        String role = userDAO.getRole(id);

        if (role == null) {

            System.out.println("Role Not Found");

            sc.close();
            return;
        }

        Admin adminUser = null;
        Owner ownerUser = null;
        Salesman salesmanUser = null;

        if (role.equalsIgnoreCase("ADMIN")) {

            adminUser = new Admin(id, "", password);

        } else if (role.equalsIgnoreCase("OWNER")) {

            ownerUser = new Owner(id, "", password);

        } else if (role.equalsIgnoreCase("SALESMAN")) {

            salesmanUser = new Salesman(
                    id,
                    "",
                    password,
                    "SALESMAN");

            salesmanUser.loadAssignedOrders();
        }

        int exit = 0;

        while (exit == 0) {

            if (role.equalsIgnoreCase("ADMIN")) {

                System.out.println("\n========== ADMIN PANEL ==========");
                System.out.println("1. Add Product");
                System.out.println("2. Add Salesman");
                System.out.println("3. Add Order");
                System.out.println("4. Assign Order To Salesman");
                System.out.println("5. Check Inventory");
                System.out.println("6. Change Password");
                System.out.println("7. Logout");

                int ch = sc.nextInt();
                sc.nextLine();

                switch (ch) {

                    case 1:
                        adminUser.addProduct();
                        break;

                    case 2:
                        adminUser.addSalesMan();
                        break;

                    case 3:
                        adminUser.addOrder();
                        break;

                    case 4:
                        adminUser.assignOrderToSalesman();
                        break;

                    case 5:

                        ArrayList<Product> products =
                                adminUser.checkInventory();

                        if (products.isEmpty()) {

                            System.out.println("Inventory Empty");
                        } else {

                            for (Product p : products) {

                                System.out.println(p);
                                System.out.println("--------------------------------");
                            }
                        }
                        break;

                    case 6:
                        adminUser.changeAdminPassword();
                        break;

                    case 7:
                        adminUser.logout();
                        exit = 1;
                        break;

                    default:
                        System.out.println("Invalid Choice");
                }
            }

            else if (role.equalsIgnoreCase("OWNER")) {

                System.out.println("\n========== OWNER PANEL ==========");
                System.out.println("1. Daily Sales");
                System.out.println("2. Total Orders");
                System.out.println("3. Total Profit");
                System.out.println("4. Total Sales");
                System.out.println("5. Inventory");
                System.out.println("6. Most Selling Product");
                System.out.println("7. Least Selling Product");
                System.out.println("8. Most Profitable Product");
                System.out.println("9. Least Profitable Product");
                System.out.println("10. Change Password");
                System.out.println("11. Logout");

                int ch = sc.nextInt();
                sc.nextLine();

                switch (ch) {

                    case 1:
                        System.out.println(ownerUser.dailySales());
                        break;

                    case 2:
                        System.out.println(ownerUser.totalOrders());
                        break;

                    case 3:
                        System.out.println(ownerUser.totalProfit());
                        break;

                    case 4:
                        System.out.println(ownerUser.totalSales());
                        break;

                    case 5:

                        ArrayList<Product> products =
                                ownerUser.inventory();

                        if (products.isEmpty()) {

                            System.out.println("Inventory Empty");
                        } else {

                            for (Product p : products) {

                                System.out.println(p);
                                System.out.println("--------------------------------");
                            }
                        }
                        break;

                    case 6:
                        System.out.println(ownerUser.mostSellingProduct());
                        break;

                    case 7:
                        System.out.println(ownerUser.leastSellingProduct());
                        break;

                    case 8:
                        System.out.println(ownerUser.mostProfitableProduct());
                        break;

                    case 9:
                        System.out.println(ownerUser.leastProfitableProduct());
                        break;

                    case 10:
                        ownerUser.changeOwnerPassword();
                        break;

                    case 11:
                        ownerUser.logout();
                        exit = 1;
                        break;
                }
            }

            else if (role.equalsIgnoreCase("SALESMAN")) {

                System.out.println("\n========== SALESMAN PANEL ==========");
                System.out.println("1. Pending Orders");
                System.out.println("2. Delivered Orders");
                System.out.println("3. Deliver Next Order");
                System.out.println("4. Salary");
                System.out.println("5. Change Password");
                System.out.println("6. Logout");

                int ch = sc.nextInt();
                sc.nextLine();

                switch (ch) {

                    case 1:
                        salesmanUser.showPendingOrders();
                        break;

                    case 2:
                        salesmanUser.checkDeliveredOrders();
                        break;

                    case 3:
                        salesmanUser.updateOrder();
                        break;

                    case 4:
                        System.out.println(
                                salesmanUser.calculateSalary());
                        break;

                    case 5:
                        salesmanUser.changeSalesmanPassword();
                        break;

                    case 6:
                        salesmanUser.logout();
                        exit = 1;
                        break;

                    default:
                        System.out.println("Invalid Choice");
                }
            }
        }

        sc.close();
    }
}