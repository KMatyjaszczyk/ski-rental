package pl.itkurnik.skirental.domain.user.exception;

public class CreateUserValidationException extends RuntimeException {

    public CreateUserValidationException(String details) {
        super(String.format("User create validation failed. Details: %s", details));
    }
}
