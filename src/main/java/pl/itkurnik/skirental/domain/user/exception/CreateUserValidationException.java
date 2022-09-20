package pl.itkurnik.skirental.domain.user.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class CreateUserValidationException extends ValidationException {

    public CreateUserValidationException(String details) {
        super(String.format("User create validation failed. Details: %s", details));
    }
}
