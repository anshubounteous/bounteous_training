package service;

import model.Product;

import java.util.*;

public class ProductCatalogueServiceImpl implements IProductCatalogueService {
    private final Map<Product, Integer> catalogue = new HashMap<>();

    @Override
    public boolean addProduct(Product product, int quantity) {
        if (catalogue.containsKey(product)) return false;
        catalogue.put(product, quantity);
        return true;
    }

    @Override
    public boolean updateProduct(Product product, int newQuantity) {
        if (!catalogue.containsKey(product)) return false;
        catalogue.put(product, newQuantity);
        return true;
    }

    @Override
    public boolean deleteProduct(Product product) {
        return catalogue.remove(product) != null;
    }

    @Override
    public Map<Product, Integer> getAllProducts() {
        return catalogue;
    }

    @Override
    public Integer getQuantity(Product product) {
        return catalogue.getOrDefault(product, null);
    }

    @Override
    public List<Map.Entry<Product, Integer>> getSortedProducts(String sortBy) {
        List<Map.Entry<Product, Integer>> sortedList = new ArrayList<>(catalogue.entrySet());

        switch (sortBy.toLowerCase()) {
            case "id":
                sortedList.sort(Comparator.comparingInt(e -> e.getKey().getProductId()));
                break;
            case "name":
                sortedList.sort(Comparator.comparing(e -> e.getKey().getName().toLowerCase()));
                break;
        }

        return sortedList;
    }
}
