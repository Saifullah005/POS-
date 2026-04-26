import java.util.ArrayList;
import java.time.LocalDate;
import java.time.DateTimeException;

public class Admin extends User {

    // Stores all added products
    private ArrayList<Product> records = new ArrayList<>();
    // Stores all the orders by month(After each month data is transfered to the
    // database)
    private ArrayList<Order> orderRecord = new ArrayList<>();
    private ArrayList<ProductSummary> trackerList = new ArrayList<>();

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
        // We need to add sellingPrice and cost price in the place of m and y
        Order o = new Order(id, pid, name, q, m, y, orderDate);
        orderRecord.add(o);
        updateQuantity(o);

        System.out.println("The order was placed successfully.");

    }

    public void updateQuantity(Order o) {
        for (int i = 0; i < trackerList.size(); i++) {
            ProductSummary ps = trackerList.get(i);

            if (ps.getProductId() == o.getProductId()) {
                ps.addQuantity(o.getQuantity());
                ps.addProfit(o.getProfit());
                return;
            }
        }

        trackerList.add(
                new ProductSummary(
                        o.getProductId(),
                        o.getQuantity(),
                        o.getProfit()));
    }

    public void checkInventory() {
        if (records.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("The current Inventory is: ");
        for (Product p : records) {
            System.out.println(p);
            System.out.println("-------------------------");
        }
    }

    
    public ProductSummary mostSellingByMonth() {
        MaxHeapByQuantity mostSelling;

        mostSelling = new MaxHeapByQuantity();
        for (ProductSummary ps : trackerList) {
            mostSelling.insert(ps);
        }
        return mostSelling.getMax();
    }

    public ProductSummary mostProfitableByMonth() {
        MaxHeapByProfit mosProfit;
        mosProfit = new MaxHeapByProfit();
        for (ProductSummary ps : trackerList) {
            mosProfit.insert(ps);
        }
        return mosProfit.getMax();
    }

    public ArrayList<ProductSummary> MergeDuplicates(LocalDate today){
            ArrayList<ProductSummary> dailyTracker = new ArrayList<>();
       
        for (Order o : orderRecord) {

            if (o.getDate().equals(today)) {

                boolean found = false;

                for (ProductSummary ps : dailyTracker) {
                    if (ps.getProductId() == o.getProductId()) {
                        ps.addQuantity(o.getQuantity());
                        ps.addProfit(o.getProfit());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    dailyTracker.add(
                            new ProductSummary(
                                    o.getProductId(),
                                    o.getQuantity(),
                                    o.getProfit()));
                }

            }
        }
        return dailyTracker;
    }

    public void DailymostSelling() {
         LocalDate today = LocalDate.now();
        ArrayList<ProductSummary>dailyTracker = MergeDuplicates(today);
        ProductSummary max = null;
        for (ProductSummary ps : dailyTracker) {
            if (max == null || ps.getTotalQuantity() > max.getTotalQuantity()) {
                max = ps;
            }

        }
        if (max != null)
            System.out.println("The most selling product today was: " + max.toString());
    }

    public void DailyProfitable() {
        ProductSummary max = null;
        LocalDate today = LocalDate.now();
        ArrayList<ProductSummary>dailyTracker = MergeDuplicates(today);
        for (ProductSummary ps : dailyTracker) {
            if (max == null || ps.getTotalProfit() > max.getTotalProfit()) {
                max = ps;
            }

        }
        if (max != null)
            System.out.println("The most selling product today was: " + max.toString());
    }

    public void changeAdminPassword() {
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
        System.out.println("=== login as an admin ===");
        System.out.println("Enter your password: ");
        String pass = sc.nextLine();
        super.login(pass);
    }

}