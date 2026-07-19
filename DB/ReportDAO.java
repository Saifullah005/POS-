package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDAO {

    public String totalOrders() {

        String sql =
                "SELECT COUNT(*) total_orders " +
                "FROM orders";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                return "Total Orders: "
                        + rs.getInt(
                                "total_orders");
            }

        } catch (SQLException e) {

            return e.getMessage();
        }

        return "Total Orders: 0";
    }

    public String totalProfit() {

        String sql =
                "SELECT IFNULL(SUM(total_profit),0) profit " +
                "FROM orders";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                return "Total Profit: "
                        + rs.getDouble(
                                "profit");
            }

        } catch (SQLException e) {

            return e.getMessage();
        }

        return "Total Profit: 0";
    }

    public String totalSales() {

        String sql =
                "SELECT IFNULL(SUM(total_price),0) sales " +
                "FROM orders";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                return "Total Sales: "
                        + rs.getDouble(
                                "sales");
            }

        } catch (SQLException e) {

            return e.getMessage();
        }

        return "Total Sales: 0";
    }

    public String mostSellingProduct() {

        String sql =
                "SELECT product_id, " +
                "SUM(quantity) total_qty " +
                "FROM order_items " +
                "GROUP BY product_id " +
                "ORDER BY total_qty DESC " +
                "LIMIT 1";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                return "Most Selling Product ID: "
                        + rs.getInt(
                                "product_id")
                        + "\nQuantity Sold: "
                        + rs.getInt(
                                "total_qty");
            }

        } catch (SQLException e) {

            return e.getMessage();
        }

        return "No Data Available";
    }


    public String leastSellingProduct() {

        String sql =
                "SELECT product_id, " +
                "SUM(quantity) total_qty " +
                "FROM order_items " +
                "GROUP BY product_id " +
                "ORDER BY total_qty ASC " +
                "LIMIT 1";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                return "Least Selling Product ID: "
                        + rs.getInt(
                                "product_id")
                        + "\nQuantity Sold: "
                        + rs.getInt(
                                "total_qty");
            }

        } catch (SQLException e) {

            return e.getMessage();
        }

        return "No Data Available";
    }

    public String mostProfitableProduct() {

        String sql =
                "SELECT product_id, " +
                "SUM((selling_price - cost_price) * quantity) total_profit " +
                "FROM order_items " +
                "GROUP BY product_id " +
                "ORDER BY total_profit DESC " +
                "LIMIT 1";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                return "Most Profitable Product ID: "
                        + rs.getInt(
                                "product_id")
                        + "\nProfit: "
                        + rs.getDouble(
                                "total_profit");
            }

        } catch (SQLException e) {

            return e.getMessage();
        }

        return "No Data Available";
    }


    public String leastProfitableProduct() {

        String sql =
                "SELECT product_id, " +
                "SUM((selling_price - cost_price) * quantity) total_profit " +
                "FROM order_items " +
                "GROUP BY product_id " +
                "ORDER BY total_profit ASC " +
                "LIMIT 1";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                return "Least Profitable Product ID: "
                        + rs.getInt(
                                "product_id")
                        + "\nProfit: "
                        + rs.getDouble(
                                "total_profit");
            }

        } catch (SQLException e) {

            return e.getMessage();
        }

        return "No Data Available";
    }

    public String todaySales() {

        String sql =
                "SELECT IFNULL(SUM(total_price),0) sales " +
                "FROM orders " +
                "WHERE order_date = CURDATE()";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                return "Today's Sales: "
                        + rs.getDouble(
                                "sales");
            }

        } catch (SQLException e) {

            return e.getMessage();
        }

        return "Today's Sales: 0";
    }

    public String todayProfit() {

        String sql =
                "SELECT IFNULL(SUM(total_profit),0) profit " +
                "FROM orders " +
                "WHERE order_date = CURDATE()";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                return "Today's Profit: "
                        + rs.getDouble(
                                "profit");
            }

        } catch (SQLException e) {

            return e.getMessage();
        }

        return "Today's Profit: 0";
    }
}