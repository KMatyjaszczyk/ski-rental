package pl.itkurnik.skirental.domain.report.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class CreateReportTypeValidationException extends ValidationException {

    public CreateReportTypeValidationException(String details) {
        super(String.format("Create report type validation failed. Details: %s", details));
    }
}
