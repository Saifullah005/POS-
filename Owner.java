public class Owner extends User{

    Admin_2 admin;
    
    Owner(){}

    Owner(Admin_2 admin){
     super();
     this.admin = admin;
    }
    
     void dailySales(){
        admin.DailymostSelling();
    }
    
    void dailyPopular(){
        admin.DailyProfitable();
    }
    
    void MonthlyPopular(){
        ProductSummary ps = admin.mostProfitableByMonth();
        if(ps != null){
            System.out.println("Most profitable product was: "  + ps);
        }
        else{
            System.out.println("No sales today");
        }
    }
    void monthlymostsellig(){
         ProductSummary ps = admin.mostSellingByMonth();
         if(ps != null){
            System.out.println("Most selling product was: " + ps);
         }
         else{
            System.out.println("No sales today");
         }
    }

     void leastsellingproduct(){
        
    ProductSummary ps = admin.leastSellingByMonth();
        if (ps != null) {
            System.out.println("Least selling product: " + ps);
        }
    }
    void dailymostselling(){
        admin.DailymostSelling(); 
    }

    public void viewTotals() {
        System.out.println("Total orders: " + admin.getTotalOrders());
        System.out.println("Total profit: " + admin.getTotalProfit());
        System.out.println("Total sales: " + admin.getTotalSales());
    }   
    
    void viewEmployeeSalaries(){
    }
    
     public void changeOwnerPassword() {
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
        System.out.println("=== login as an Owner ===");
        System.out.println("Enter your password: ");
        String pass = sc.nextLine();
        super.login(pass);
    }
    
    
}