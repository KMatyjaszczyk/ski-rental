package pl.itkurnik.skirental.domain.equipment.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.equipment.dto.UpdateSizeRequest;
import pl.itkurnik.skirental.domain.equipment.exception.UpdateSizeValidationException;
import pl.itkurnik.skirental.util.validation.EmptyStringValidator;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;

@Component
public class UpdateSizeValidator extends MultipleFieldsValidator<UpdateSizeRequest> {

    @Override
    protected void processFieldsValidation(UpdateSizeRequest request, List<String> errorMessages) {
        NullObjectValidator.validate(request.getId(), "Size id", errorMessages);
        EmptyStringValidator.validate(request.getSize(), "Size", errorMessages);
        NullObjectValidator.validate(request.getEquipmentCategoryId(), "Equipment category id", errorMessages);
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return UpdateSizeValidationException.class;
    }
}
