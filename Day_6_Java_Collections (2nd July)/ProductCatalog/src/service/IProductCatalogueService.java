package service;

import model.Product;
import java.util.List;
import java.util.Map;

public interface IProductCatalogueService {
    boolean addProduct(Product product, int quantity);
    boolean updateProduct(Product product, int newQuantity);
    boolean deleteProduct(Product product);
    Map<Product, Integer> getAllProducts();
    List<Map.Entry<Product, Integer>> getSortedProducts(String sortBy);
    Integer getQuantity(Product product);
}
