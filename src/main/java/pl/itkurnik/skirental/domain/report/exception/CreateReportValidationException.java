package pl.itkurnik.skirental.domain.report.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class CreateReportValidationException extends ValidationException {

    public CreateReportValidationException(String details) {
        super(String.format("Create report validation failed. Details: %s", details));
    }
}
