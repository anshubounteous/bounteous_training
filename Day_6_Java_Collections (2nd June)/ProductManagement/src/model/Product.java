package model;

import java.util.Objects;

public class Product {
    private int product_id;
    private String product_name;
    private String product_category;
    private double product_price;

    public Product(int product_id, String product_name, String product_category, double product_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_category = product_category;
        this.product_price = product_price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return product_id == product.product_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Category: %s | Price: %.2f",
                product_id, product_name, product_category, product_price);
    }
}
