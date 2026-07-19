package src;

import java.util.ArrayList;

import DB.ReportDAO;

public class Owner extends User {

    private ReportDAO reportDAO;

    private Admin admin;

    public Owner() {
        super();
        reportDAO = new ReportDAO();
        admin = new Admin();
    }

    public Owner(int id, String name, String password) {
        super(id, name, password, "OWNER");
        reportDAO = new ReportDAO();
        admin = new Admin();
    }

    public String dailySales() {
        return reportDAO.todaySales() + "\n" + reportDAO.todayProfit();
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

    public ArrayList<Product> inventory() {
        return admin.checkInventory();
    }

    public void changeOwnerPassword() {

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

        System.out.println("=== Login As Owner ===");
        System.out.print("Enter password: ");

        String pass = sc.nextLine();

        super.login(pass);
    }

}