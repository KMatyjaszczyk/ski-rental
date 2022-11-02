package pl.itkurnik.skirental.domain.rent.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rent.dto.UpdateClientDocumentTypeRequest;
import pl.itkurnik.skirental.domain.rent.exception.UpdateClientDocumentTypeValidationException;
import pl.itkurnik.skirental.util.validation.EmptyStringValidator;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;

@Component
public class UpdateClientDocumentTypeValidator extends MultipleFieldsValidator<UpdateClientDocumentTypeRequest> {

    @Override
    protected void processFieldsValidation(UpdateClientDocumentTypeRequest request, List<String> errorMessages) {
        NullObjectValidator.validate(request.getId(), "Client document type id", errorMessages);
        EmptyStringValidator.validate(request.getName(), "Description", errorMessages);
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return UpdateClientDocumentTypeValidationException.class;
    }
}
