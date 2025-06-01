package service;

import model.TaxPayer;

public interface TaxCalculatorService {
    double calculateTax(TaxPayer taxPayer);
}
