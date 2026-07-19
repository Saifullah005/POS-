package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection{
    /*This class establishes the connection and returns the object of connection for 
    the object of each DAO*/
    public static Connection getConnection(){
        /*The purpose to keep the method static is to make sure that we don't need to make 
        additional object for the sake of this connection object*/
        try{
            return DriverManager.getConnection(
                 "jdbc:mysql://localhost:3306/POS",
                "root","root"
            );
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}