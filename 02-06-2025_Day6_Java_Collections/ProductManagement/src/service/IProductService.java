package service;

import model.Product;
import java.util.*;

public interface IProductService {
    boolean addProduct(Product p);
    boolean updateProduct(int product_id, String product_name, String product_category, double product_price);
    boolean deleteProduct(int product_id);
    Product getProductById(int product_id);
    List<Product> getAllProductsSorted(String criteria); // ✅ Fixed name
    Set<Product> getAllProducts();
}
