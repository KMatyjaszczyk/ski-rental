package pl.itkurnik.skirental.domain.item.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class CreateItemValidationException extends ValidationException {

    public CreateItemValidationException(String details) {
        super(String.format("Item create validation failed. Details: %s", details));
    }
}
