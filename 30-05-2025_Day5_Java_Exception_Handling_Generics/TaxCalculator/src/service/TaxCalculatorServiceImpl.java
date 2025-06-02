package service;

import model.TaxPayer;

public class TaxCalculatorServiceImpl implements TaxCalculatorService {

    @Override
    public double calculateTax(TaxPayer p) {
        double grossIncome = p.getSalary();

        // Deductions
        double deduction80C = Math.min(p.getInvestment(), 150000);
        double deduction80D = (p.getAge() >= 60) ? Math.min(p.getHealthInsurance(), 50000) : Math.min(p.getHealthInsurance(), 25000);
        double deduction24 = Math.min(p.getHomeLoanInterest(), 200000);

        double totalDeductions = deduction80C + deduction80D + deduction24;
        double taxableIncome = Math.max(grossIncome - totalDeductions, 0);

        return calculateTaxBySlab(p.getAge(), taxableIncome);
    }

    private double calculateTaxBySlab(int age, double income) {
        double tax = 0;

        if (age < 60) {
            if (income <= 250000) return 0;
            if (income <= 500000) return (income - 250000) * 0.05;
            if (income <= 1000000)
                return (250000 * 0.05) + (income - 500000) * 0.2;
            return (250000 * 0.05) + (500000 * 0.2) + (income - 1000000) * 0.3;
        } else if (age < 80) {
            if (income <= 300000) return 0;
            if (income <= 500000) return (income - 300000) * 0.05;
            if (income <= 1000000)
                return (200000 * 0.05) + (income - 500000) * 0.2;
            return (200000 * 0.05) + (500000 * 0.2) + (income - 1000000) * 0.3;
        } else {
            if (income <= 500000) return 0;
            if (income <= 1000000)
                return (income - 500000) * 0.2;
            return (500000 * 0.2) + (income - 1000000) * 0.3;
        }
    }
}
