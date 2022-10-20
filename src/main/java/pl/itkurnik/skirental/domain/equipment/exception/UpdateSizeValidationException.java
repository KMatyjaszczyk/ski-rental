package pl.itkurnik.skirental.domain.equipment.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class UpdateSizeValidationException extends ValidationException {

    public UpdateSizeValidationException(String details) {
        super(String.format("Size update validation failed. Details: %s", details));
    }
}
