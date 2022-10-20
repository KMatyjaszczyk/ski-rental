package pl.itkurnik.skirental.domain.equipment.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.equipment.dto.UpdateEquipmentRequest;
import pl.itkurnik.skirental.domain.equipment.exception.UpdateEquipmentValidationException;
import pl.itkurnik.skirental.util.validation.EmptyStringValidator;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;

@Component
public class UpdateEquipmentValidator extends MultipleFieldsValidator<UpdateEquipmentRequest> {
    @Override
    protected void processFieldsValidation(UpdateEquipmentRequest request, List<String> errorMessages) {
        NullObjectValidator.validate(request.getId(), "Equipment id", errorMessages);
        EmptyStringValidator.validate(request.getModel(), "Model", errorMessages);
        NullObjectValidator.validate(request.getManufacturerId(), "Manufacturer id", errorMessages);
        NullObjectValidator.validate(request.getEquipmentCategoryId(), "Equipment category id", errorMessages);
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return UpdateEquipmentValidationException.class;
    }
}
