package pl.itkurnik.skirental.domain.rent.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class ReturnRentItemsValidationException extends ValidationException {

    public ReturnRentItemsValidationException(String details) {
        super(String.format("Return rent items validation failed. Details: %s", details));
    }
}
