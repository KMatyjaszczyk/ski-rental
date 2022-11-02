package pl.itkurnik.skirental.domain.rent.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class UpdateClientDocumentTypeValidationException extends ValidationException {

    public UpdateClientDocumentTypeValidationException(String details) {
        super(String.format("Update document type create validation failed. Details: %s", details));
    }
}
