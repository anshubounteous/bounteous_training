package model;

public class TaxPayer {
    private int age;
    private double salary;
    private double investment;
    private double healthInsurance;
    private double homeLoanInterest;

    public TaxPayer(int age, double salary, double investment, double healthInsurance, double homeLoanInterest) {
        this.age = age;
        this.salary = salary;
        this.investment = investment;
        this.healthInsurance = healthInsurance;
        this.homeLoanInterest = homeLoanInterest;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public double getInvestment() {
        return investment;
    }

    public double getHealthInsurance() {
        return healthInsurance;
    }

    public double getHomeLoanInterest() {
        return homeLoanInterest;
    }
}
