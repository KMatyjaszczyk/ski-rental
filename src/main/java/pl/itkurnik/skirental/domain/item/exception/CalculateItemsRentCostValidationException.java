package pl.itkurnik.skirental.domain.item.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class CalculateItemsRentCostValidationException extends ValidationException {

    public CalculateItemsRentCostValidationException(String details) {
        super(String.format("Calculate items rent cost validation failed. Details: %s", details));
    }
}
