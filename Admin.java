import java.util.ArrayList;
import java.time.LocalDate;
import java.time.DateTimeException;

public class Admin extends User {
    private ArrayList<Salesman2> salesman = new ArrayList<>();
    // Stores all added products
    private ArrayList<Product> records = new ArrayList<>();
    // Stores all the orders by month(After each month data is transfered to the
    // database)
    private ArrayList<Order> orderRecord = new ArrayList<>();
    private ArrayList<ProductSummary> trackerList = new ArrayList<>();
    private MaxHeapByProfit profitHeap;
    private MaxHeapByQuantity quantityHeap;
    private int totalOrders ;
    double totalProfit , totalSales;
    boolean heapify = true;

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

      salesman.add(new Salesman2(this, id, phoneNumber, name, add));
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

        System.out.print("Enter cost price per item: ");
        double cp = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter sale price per item: ");
        double sp = sc.nextDouble();
        records.add(new Product(id, quantity, sp, cp, productName, expiryDate));

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

        Product selected = null;
        for (Product p : records) {

            if (p.getId() == pid) {
                selected = p;
                break;
            }
        }
        if(selected == null){
            System.out.println("Product not found");
            return;
        }
    
        
        if (!selected.soldFromStock(q)) {
            System.out.println("Insufficient stock. Order not executed.");
            return;
        }
        
        Order o = new Order(id, pid, name, q, selected.getSalePrice(), selected.getCostPrice(), orderDate);
        orderRecord.add(o);
        updateQuantity(o);

        totalOrders++;
        totalProfit += o.getProfit();
        totalSales += o.getTotal();

        System.out.println("The order was placed successfully.");
        heapify = true;

    }

    
    public void assignOrderToSalesman(Order o, int salesmanIndex) {
        if (salesmanIndex < 0 || salesmanIndex >= salesman.size()) {
            System.out.println("Invalid salesman selection.");
            return;
        }
        salesman.get(salesmanIndex).assignOrder(o);
    }

    public int getTotalOrders(){
        return this.totalOrders;
    }

    public double getTotalProfit(){
        return this.totalProfit;
    }

    public double getTotalSales(){
        return this.totalSales;
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

    private void buildHeaps() {
        if (!heapify)
            return;

        quantityHeap = new MaxHeapByQuantity();
        profitHeap = new MaxHeapByProfit();

        for (ProductSummary ps : trackerList) {
            quantityHeap.insert(ps);
            profitHeap.insert(ps);
        }

        heapify = false;
    }

    public ProductSummary mostSellingByMonth() {
        buildHeaps();
        return quantityHeap.getMax();
    }

    public ProductSummary mostProfitableByMonth() {
        buildHeaps();
        return profitHeap.getMax();
    }

    public ProductSummary leastSellingByMonth() {
        if (trackerList.isEmpty())
            return null;

        ProductSummary min = trackerList.get(0);

        for (ProductSummary ps : trackerList) {
            if (ps.getTotalQuantity() < min.getTotalQuantity()) {
                min = ps;
            }
        }

        return min;
    }

    public ProductSummary leastProfitableByMonth() {
        if (trackerList.isEmpty())
            return null;

        ProductSummary min = trackerList.get(0);

        for (ProductSummary ps : trackerList) {
            if (ps.getTotalProfit() < min.getTotalProfit()) {
                min = ps;
            }
        }

        return min;
    }

    public ArrayList<ProductSummary> MergeDuplicates(LocalDate today) {
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
        ArrayList<ProductSummary> dailyTracker = MergeDuplicates(today);
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
        ArrayList<ProductSummary> dailyTracker = MergeDuplicates(today);
        for (ProductSummary ps : dailyTracker) {
            if (max == null || ps.getTotalProfit() > max.getTotalProfit()) {
                max = ps;
            }

        }
        if (max != null)
            System.out.println("The most profitable product today was: " + max.toString());
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