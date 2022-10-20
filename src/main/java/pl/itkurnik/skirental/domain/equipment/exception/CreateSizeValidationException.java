package pl.itkurnik.skirental.domain.equipment.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class CreateSizeValidationException extends ValidationException {

    public CreateSizeValidationException(String details) {
        super(String.format("Size create validation failed. Details: %s", details));
    }
}
