import model.Product;
import service.IProductService;
import service.ProductServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IProductService service = new ProductServiceImpl();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Product Manager ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Product by ID");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. View All Products");
            System.out.println("6. Sort Products");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("ID: "); int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Category: "); String category = sc.nextLine();
                    System.out.print("Price: "); double price = sc.nextDouble();
                    boolean added = service.addProduct(new Product(id, name, category, price));
                    System.out.println(added ? "Added Successfully." : "Duplicate ID not allowed.");
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    Product found = service.getProductById(sc.nextInt());
                    System.out.println(found != null ? found : "Product Not Found.");
                    break;

                case 3:
                    System.out.print("Enter Product ID to Update: ");
                    int uid = sc.nextInt(); sc.nextLine();
                    System.out.print("New Name: "); String n = sc.nextLine();
                    System.out.print("New Category: "); String c = sc.nextLine();
                    System.out.print("New Price: "); double pr = sc.nextDouble();
                    boolean updated = service.updateProduct(uid, n, c, pr);
                    System.out.println(updated ? "Updated Successfully." : "Product Not Found.");
                    break;

                case 4:
                    System.out.print("Enter ID to Delete: ");
                    boolean deleted = service.deleteProduct(sc.nextInt());
                    System.out.println(deleted ? "Deleted Successfully." : "Product Not Found.");
                    break;

                case 5:
                    service.getAllProducts().forEach(System.out::println);
                    break;

                case 6:
                    System.out.print("Sort by (id/name/price): ");
                    List<Product> sorted = service.getAllProductsSorted(sc.next());
                    sorted.forEach(System.out::println);
                    break;

                case 0:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
