package pl.itkurnik.skirental.domain.item.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class UpdateItemValidationException extends ValidationException {

    public UpdateItemValidationException(String details) {
        super(String.format("Item update validation failed. Details: %s", details));
    }
}
