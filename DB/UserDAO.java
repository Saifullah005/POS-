package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public boolean registerUser(String name, String password, String role) {

        if (name == null || name.isBlank()) {
            return false;
        }

        if (password == null || password.isBlank()) {
            return false;
        }

        if (role == null || role.isBlank()) {
            return false;
        }

        Connection con = DBConnection.getConnection();

        if (con == null) {
            return false;
        }

        try {

            String sql = "INSERT INTO users(user_name, user_password, role) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, name);
            st.setString(2, password);
            st.setString(3, role);

            return st.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public boolean addUser(
            String name,
            String password,
            String role) {

        return registerUser(
                name,
                password,
                role);
    }

    public boolean loginUser(int id, String password) {

        if (id <= 0) {
            return false;
        }

        if (password == null || password.isBlank()) {
            return false;
        }

        Connection con = DBConnection.getConnection();

        if (con == null) {
            return false;
        }

        try {

            String sql = "SELECT * FROM users WHERE user_id = ? AND user_password = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public boolean changePassword(
            int id,
            String password) {

        if (id <= 0) {
            return false;
        }

        if (password == null || password.isBlank()) {
            return false;
        }

        Connection con = DBConnection.getConnection();

        if (con == null) {
            return false;
        }

        try {

            String sql = "UPDATE users " +
                    "SET user_password=? " +
                    "WHERE user_id=?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, password);
            st.setInt(2, id);

            return st.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public String getRole(
            String name,
            String password) {

        if (name == null || name.isBlank()) {
            return null;
        }

        if (password == null || password.isBlank()) {
            return null;
        }

        Connection con = DBConnection.getConnection();

        if (con == null) {
            return null;
        }

        try {

            String sql = "SELECT role " +
                    "FROM users " +
                    "WHERE user_name=? " +
                    "AND user_password=?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, name);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                return rs.getString(
                        "role");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public String getRole(int id) {

        if (id <= 0) {
            return null;
        }

        Connection con = DBConnection.getConnection();

        if (con == null) {
            return null;
        }

        try {

            String sql = "SELECT role FROM users WHERE user_id = ?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                return rs.getString("role");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
    public int getId(
            String name,
            String password) {

        if (name == null || name.isBlank()) {
            return 0;
        }

        if (password == null || password.isBlank()) {
            return 0;
        }

        Connection con = DBConnection.getConnection();

        if (con == null) {
            return 0;
        }

        try {

            String sql = "SELECT user_id " +
                    "FROM users " +
                    "WHERE user_name=? " +
                    "AND user_password=?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, name);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                return rs.getInt(
                        "user_id");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    public boolean deleteUser(
            int id) {

        if (id <= 0) {
            return false;
        }

        Connection con = DBConnection.getConnection();

        if (con == null) {
            return false;
        }

        try {

            String sql = "DELETE FROM users " +
                    "WHERE user_id=?";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            return st.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}