package pl.itkurnik.skirental.domain.report.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.report.dto.CreateReportRequest;
import pl.itkurnik.skirental.domain.report.exception.CreateReportValidationException;
import pl.itkurnik.skirental.util.validation.EmptyStringValidator;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;

@Component
public class CreateReportFieldsValidator extends MultipleFieldsValidator<CreateReportRequest> {

    @Override
    protected void processFieldsValidation(CreateReportRequest request, List<String> errorMessages) {
        NullObjectValidator.validate(request.getRentId(), "Rent id", errorMessages);
        NullObjectValidator.validate(request.getReportTypeId(), "Report type id", errorMessages);
        EmptyStringValidator.validate(request.getDescription(), "Report description", errorMessages);
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return CreateReportValidationException.class;
    }
}
