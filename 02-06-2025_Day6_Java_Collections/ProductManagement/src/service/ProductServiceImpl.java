package service;

import model.Product;
import util.ProductComparator;

import java.util.*;

public class ProductServiceImpl implements IProductService {
    private Set<Product> products = new HashSet<>();

    @Override
    public boolean addProduct(Product p) {
        return products.add(p);
    }

    @Override
    public boolean updateProduct(int product_id, String product_name, String product_category, double product_price) {
        for (Product p : products) {
            if (p.getProduct_id() == product_id) {
                p.setProduct_name(product_name);
                p.setProduct_category(product_category);
                p.setProduct_price(product_price);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int product_id) {
        return products.removeIf(p -> p.getProduct_id() == product_id);
    }

    @Override
    public Product getProductById(int product_id) {
        return products.stream().filter(p -> p.getProduct_id() == product_id).findFirst().orElse(null);
    }

    @Override
    public List<Product> getAllProductsSorted(String criteria) {
        List<Product> sortedList = new ArrayList<>(products);
        sortedList.sort(new util.ProductComparator(criteria));
        return sortedList;
    }

    @Override
    public Set<Product> getAllProducts() {
        return products;
    }
}
