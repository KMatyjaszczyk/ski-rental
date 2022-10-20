package pl.itkurnik.skirental.domain.equipment.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.equipment.dto.CreateEquipmentRequest;
import pl.itkurnik.skirental.domain.equipment.exception.CreateEquipmentValidationException;
import pl.itkurnik.skirental.util.validation.EmptyStringValidator;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;

@Component
public class CreateEquipmentValidator extends MultipleFieldsValidator<CreateEquipmentRequest> {
    @Override
    protected void processFieldsValidation(CreateEquipmentRequest request, List<String> errorMessages) {
        EmptyStringValidator.validate(request.getModel(), "Model", errorMessages);
        NullObjectValidator.validate(request.getManufacturerId(), "Manufacturer id", errorMessages);
        NullObjectValidator.validate(request.getEquipmentCategoryId(), "Equipment category id", errorMessages);
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return CreateEquipmentValidationException.class;
    }
}
