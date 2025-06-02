package util;

import model.Product;
import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {
    private final String criteria;

    public ProductComparator(String criteria) {
        this.criteria = criteria;
    }

    @Override
    public int compare(Product p1, Product p2) {
        switch (criteria.toLowerCase()) {
            case "name":
                return p1.getProduct_name().compareToIgnoreCase(p2.getProduct_name());
            case "id":
                return Integer.compare(p1.getProduct_id(), p2.getProduct_id());
            case "price":
                return Double.compare(p1.getProduct_price(), p2.getProduct_price());
            default:
                return 0;
        }
    }
}
