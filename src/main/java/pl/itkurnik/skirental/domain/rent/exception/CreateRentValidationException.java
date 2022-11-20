package pl.itkurnik.skirental.domain.rent.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class CreateRentValidationException extends ValidationException {

    public CreateRentValidationException(String details) {
        super(String.format("Rent create validation failed. Details: %s", details));
    }
}
