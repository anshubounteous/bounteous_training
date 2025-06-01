package ui;

import model.TaxPayer;
import service.*;

import java.util.Scanner;

public class TaxCalculatorCLI {
    private final Scanner scanner = new Scanner(System.in);
    private final TaxCalculatorService service = new TaxCalculatorServiceImpl();

    public void run() {
        System.out.println("Welcome to the Income Tax Calculator");

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.print("Enter your annual salary (INR): ");
        double salary = scanner.nextDouble();

        System.out.print("Enter your investment in tax-saving instruments (INR): ");
        double investment = scanner.nextDouble();

        System.out.print("Enter your annual health insurance premium (INR): ");
        double healthInsurance = scanner.nextDouble();

        System.out.print("Enter your annual home loan interest (INR): ");
        double homeLoan = scanner.nextDouble();

        TaxPayer taxpayer = new TaxPayer(age, salary, investment, healthInsurance, homeLoan);
        double tax = service.calculateTax(taxpayer);

        System.out.printf("Total tax owed: ₹%.2f\n", tax);
    }
}
