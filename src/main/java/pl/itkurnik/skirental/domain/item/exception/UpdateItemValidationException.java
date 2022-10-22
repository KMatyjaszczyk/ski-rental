package pl.itkurnik.skirental.domain.item.exception;

public class UpdateItemValidationException extends RuntimeException {

    public UpdateItemValidationException(String details) {
        super(String.format("Item update validation failed. Details: %s", details));
    }
}
