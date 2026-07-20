package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import src.Product;

public class ProductDAO {

    public boolean addProduct(Product p) {

        if(p == null){
            return false;
        }

        Connection con = DBConnection.getConnection();

        if(con == null){
            return false;
        }

        try{

            String sql =
            "INSERT INTO product(name,quantity,cost_price,sale_price,expirydate,lowstock_threshold) VALUES(?,?,?,?,?,?)";

            PreparedStatement st =
                    con.prepareStatement(sql);

            st.setString(1,p.getName());
            st.setInt(2,p.getQuantity());
            st.setDouble(3,p.getCostPrice());
            st.setDouble(4,p.getSalePrice());
            st.setDate(5,
                    java.sql.Date.valueOf(
                            p.getExpiryDate()
                    )
            );

            st.setInt(6,5);

            int row =
                    st.executeUpdate();

            return row > 0;

        }
        catch(Exception e){

            e.printStackTrace();
            return false;

        }
    }

    public Product getProduct(int id){

        if(id <= 0){
            return null;
        }

        Connection con =
                DBConnection.getConnection();

        if(con == null){
            return null;
        }

        try{

            String sql =
            "SELECT * FROM product WHERE product_id = ?";

            PreparedStatement st =
                    con.prepareStatement(sql);

            st.setInt(1,id);

            ResultSet rs =
                    st.executeQuery();

            if(rs.next()){

                return new Product(

                        rs.getInt("product_id"),

                        rs.getInt("quantity"),

                        rs.getDouble("sale_price"),

                        rs.getDouble("cost_price"),

                        rs.getString("name"),

                        rs.getDate("expirydate")
                                .toLocalDate()
                );
            }

            return null;

        }
        catch(Exception e){

            e.printStackTrace();
            return null;

        }
    }

    public ArrayList<Product> getAllProducts(){

        ArrayList<Product> products =
                new ArrayList<>();

        Connection con =
                DBConnection.getConnection();

        if(con == null){
            return products;
        }

        try{

            String sql =
                    "SELECT * FROM product";

            PreparedStatement st =
                    con.prepareStatement(sql);

            ResultSet rs =
                    st.executeQuery();

            while(rs.next()){

                products.add(

                        new Product(

                                rs.getInt("product_id"),

                                rs.getInt("quantity"),

                                rs.getDouble("sale_price"),

                                rs.getDouble("cost_price"),

                                rs.getString("name"),

                                rs.getDate("expirydate")
                                        .toLocalDate()
                        )
                );
            }

        }
        catch(Exception e){

            e.printStackTrace();
        }

        return products;
    }

    public boolean updateQuantity(
            int id,
            int quantity){

        if(id <= 0 || quantity < 0){
            return false;
        }

        Connection con =
                DBConnection.getConnection();

        if(con == null){
            return false;
        }

        try{

            String sql =
            "UPDATE product SET quantity = ? WHERE product_id = ?";

            PreparedStatement st =
                    con.prepareStatement(sql);

            st.setInt(1,quantity);
            st.setInt(2,id);

            int row =
                    st.executeUpdate();

            return row > 0;

        }
        catch(Exception e){

            e.printStackTrace();
            return false;

        }
    }

    public boolean updatePrice(
            int id,
            double cp,
            double sp){

        if(id <= 0){
            return false;
        }

        Connection con =
                DBConnection.getConnection();

        if(con == null){
            return false;
        }

        try{

            String sql =
            "UPDATE product SET cost_price = ?, sale_price = ? WHERE product_id = ?";

            PreparedStatement st =
                    con.prepareStatement(sql);

            st.setDouble(1,cp);
            st.setDouble(2,sp);
            st.setInt(3,id);

            int row =
                    st.executeUpdate();

            return row > 0;

        }
        catch(Exception e){

            e.printStackTrace();
            return false;

        }
    }

    public boolean removeProduct(int id){

        if(id <= 0){
            return false;
        }

        Connection con =
                DBConnection.getConnection();

        if(con == null){
            return false;
        }

        try{

            String sql =
                    "DELETE FROM product WHERE product_id = ?";

            PreparedStatement st =
                    con.prepareStatement(sql);

            st.setInt(1,id);

            int row =
                    st.executeUpdate();

            return row > 0;

        }
        catch(Exception e){

            e.printStackTrace();
            return false;

        }
    }
}