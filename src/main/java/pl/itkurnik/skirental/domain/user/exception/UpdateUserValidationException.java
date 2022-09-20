package pl.itkurnik.skirental.domain.user.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class UpdateUserValidationException extends ValidationException {

    public UpdateUserValidationException(String details) {
        super(String.format("Update user validation failed. Details: %s", details));
    }
}
