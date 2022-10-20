package pl.itkurnik.skirental.domain.equipment.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.equipment.dto.CreateSizeRequest;
import pl.itkurnik.skirental.domain.equipment.exception.CreateSizeValidationException;
import pl.itkurnik.skirental.util.validation.EmptyStringValidator;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;

@Component
public class CreateSizeValidator extends MultipleFieldsValidator<CreateSizeRequest> {
    @Override
    protected void processFieldsValidation(CreateSizeRequest request, List<String> errorMessages) {
        EmptyStringValidator.validate(request.getSize(), "Size", errorMessages);
        NullObjectValidator.validate(request.getEquipmentCategoryId(), "Equipment category id", errorMessages);
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return CreateSizeValidationException.class;
    }
}
