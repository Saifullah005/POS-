import java.util.Scanner;
public class User {

    protected int userid;
    protected String name;
    protected String password;
    protected String role;
    protected int salary;
    protected boolean loggedIn = false;
    // Console input scanner 
    protected static Scanner sc = new Scanner(System.in);

    public User() {

        System.out.print("Enter User ID: ");
        this.userid = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter Name: ");
        this.name = sc.nextLine();

        System.out.print("Enter Password: ");
        this.password = sc.nextLine();

        System.out.print("Enter Role [admin/owner/salesman]: ");
        this.role = sc.nextLine();


    }
//Later for GUI
    public User(int userid, String name, String password, String role) {
        this.userid = userid;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public boolean changePassword(String oldPass, String newPass) {
        if (!password.equals(oldPass)) {
            System.out.println("Incorrect old password.");
            return false;
        }
        password = newPass;
        System.out.println("Password changed successfully.");
        return true;
    }
    

    public void logout() {
        loggedIn = false;
        System.out.println("Logged out successfully ");
    }

    public boolean login(String inputPassword) {
        if (password.equals(inputPassword)) {
            loggedIn = true;
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Invalid password.");
            return false;
        }
    }
}
