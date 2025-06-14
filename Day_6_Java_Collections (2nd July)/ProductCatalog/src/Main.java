import model.Product;
import service.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IProductCatalogueService service = new ProductCatalogueServiceImpl();

        while (true) {
            System.out.println("\n--- Product Catalogue ---");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Update Product Quantity");
            System.out.println("4. Delete Product");
            System.out.println("5. Sort Products");
            System.out.println("6. Get Quantity by Product ID");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Product ID: "); int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Category: "); String cat = sc.nextLine();
                    System.out.print("Price: "); double price = sc.nextDouble();
                    System.out.print("Quantity: "); int qty = sc.nextInt();
                    boolean added = service.addProduct(new Product(id, name, cat, price), qty);
                    System.out.println(added ? "Product added successfully." : "Duplicate product ID.");
                    break;

                case 2:
                    service.getAllProducts().forEach((p, q) ->
                            System.out.println(p + " | Quantity: " + q));
                    break;

                case 3:
                    System.out.print("Enter Product ID to update: ");
                    int uid = sc.nextInt();
                    System.out.print("New Quantity: ");
                    int newQty = sc.nextInt();
                    Product updateTarget = findProductById(service, uid);
                    if (updateTarget != null) {
                        service.updateProduct(updateTarget, newQty);
                        System.out.println("Updated successfully.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Product ID to delete: ");
                    int did = sc.nextInt();
                    Product deleteTarget = findProductById(service, did);
                    if (deleteTarget != null) {
                        service.deleteProduct(deleteTarget);
                        System.out.println("Deleted successfully.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 5:
                    System.out.print("Sort by (id/name): ");
                    String sortBy = sc.next();
                    service.getSortedProducts(sortBy).forEach(entry ->
                            System.out.println(entry.getKey() + " | Quantity: " + entry.getValue()));
                    break;

                case 6:
                    System.out.print("Enter Product ID: ");
                    int pid = sc.nextInt();
                    Product search = findProductById(service, pid);
                    if (search != null) {
                        System.out.println("Quantity: " + service.getQuantity(search));
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static Product findProductById(IProductCatalogueService service, int id) {
        for (Product p : service.getAllProducts().keySet()) {
            if (p.getProductId() == id)
                return p;
        }
        return null;
    }
}
