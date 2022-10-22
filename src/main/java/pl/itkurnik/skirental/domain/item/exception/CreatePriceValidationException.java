package pl.itkurnik.skirental.domain.item.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class CreatePriceValidationException extends ValidationException {

    public CreatePriceValidationException(String details) {
        super(String.format("Price create validation failed. Details: %s", details));
    }
}
