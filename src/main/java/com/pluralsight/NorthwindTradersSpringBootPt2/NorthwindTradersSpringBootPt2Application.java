package com.pluralsight.NorthwindTradersSpringBootPt2;

import com.pluralsight.NorthwindTradersSpringBootPt2.model.Product;
import com.pluralsight.NorthwindTradersSpringBootPt2.services.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class NorthwindTradersSpringBootPt2Application {

	public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(NorthwindTradersSpringBootPt2Application.class, args);

        ProductService productService = context.getBean(ProductService.class);

        Scanner scanner = new Scanner(System.in); // Scanner for reading user input.
        int choice;
        do {
            // Displaying the menu options to the user.
            System.out.println("========== Ledger Application ==========");
            System.out.println("1. List Products");
            System.out.println("2. Add Products");
            System.out.println("3. Update Products");
            System.out.println("4. Delete Products");
            System.out.println("5. Search Products");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consumes the newline character after the integer input.

            // Handling user's choice with switch-case.
            switch (choice) {
                case 1:
                    listProducts(productService);
                    break;
                case 2:
                    addTransaction(scanner, productService);
                    break;
                case 3:
                    updateProduct(scanner, productService);
                    break;
                case 4:
                    deleteProduct(scanner, productService);
                    break;
                case 5:
                    searchProduct(scanner, productService);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void listProducts(ProductService productService){
        System.out.println("========== List of Products ==========");
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println();
    }

    private static void addTransaction(Scanner scanner, ProductService productService) {
        System.out.print("Enter a product name: ");
        String product = scanner.nextLine();

        System.out.print("Enter category ID:  ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter unit price: ");
        double unitPrice = scanner.nextDouble();
        scanner.nextLine();

        Product product1 = new Product(product, categoryId, unitPrice);
        Product newProduct = productService.addProduct(product1);

        System.out.println("Product Added");
        System.out.println(newProduct);
        System.out.println(" ");
    }

    private static void updateProduct(Scanner scanner, ProductService productService){
        System.out.print("Enter a product ID: ");
        int productID = scanner.nextInt();
        scanner.nextLine();

        Product checkingProduct = productService.getProductByID(productID);
        if (checkingProduct == null){
            System.out.println("Product not found");
            return;
        }

        System.out.print("Enter a product name: ");
        String product = scanner.nextLine();

        System.out.print("Enter category ID:  ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter unit price: ");
        double unitPrice = scanner.nextDouble();
        scanner.nextLine();

        Product product1 = new Product(productID,product, categoryId, unitPrice);
        productService.updateProducts(productID, product1);

        System.out.println("Updated existing Product");
        System.out.println(" ");
    }

    private static void deleteProduct(Scanner scanner, ProductService productService){
        System.out.print("Enter a product ID: ");
        int productID = scanner.nextInt();
        scanner.nextLine();

        Product checkingProduct = productService.getProductByID(productID);
        if (checkingProduct == null){
            System.out.println("Product not found");
            return;
        }

        productService.deleteProduct(productID);
        System.out.println("Product Deleted");
    }

    private static void searchProduct (Scanner scanner, ProductService productService){
        System.out.print("Enter a product ID: ");
        int productID = scanner.nextInt();
        scanner.nextLine();

        Product checkingProduct = productService.getProductByID(productID);
        if (checkingProduct == null){
            System.out.println("Product not found");
        } else {
            System.out.println("========== Product Details ==========");
            System.out.println(checkingProduct);
            System.out.println(" ");
        }
    }

}




