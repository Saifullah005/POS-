
package DB;

import java.time.LocalDate;
import java.util.ArrayList;

import src.Product;

public class TestProductDAO {

    public static void main(String[] args) {

  ProductDAO dao = new ProductDAO();

Product p =
        new Product(
                1,
                100,
                150,
                120,
                "Master Cola",
                LocalDate.of(2027,1,1)
        );

System.out.println(
        dao.addProduct(p)
);
   }
}