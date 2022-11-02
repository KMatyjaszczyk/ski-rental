package pl.itkurnik.skirental.domain.rent.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class CreateClientDocumentTypeValidationException extends ValidationException {

    public CreateClientDocumentTypeValidationException(String details) {
        super(String.format("Client document type create validation failed. Details: %s", details));
    }
}
