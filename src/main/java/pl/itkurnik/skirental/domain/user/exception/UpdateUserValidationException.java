package pl.itkurnik.skirental.domain.user.exception;

public class UpdateUserValidationException extends RuntimeException {

    public UpdateUserValidationException(String details) {
        super(String.format("Update user validation failed. Details: %s", details));
    }
}
