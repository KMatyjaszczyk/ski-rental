package pl.itkurnik.skirental.domain.equipment.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.equipment.dto.CreateEquipmentImageRequest;
import pl.itkurnik.skirental.domain.equipment.exception.CreateEquipmentImageValidationException;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;

@Component
public class CreateEquipmentImageValidator extends MultipleFieldsValidator<CreateEquipmentImageRequest> {
    @Override
    protected void processFieldsValidation(CreateEquipmentImageRequest request, List<String> errorMessages) {
        NullObjectValidator.validate(request.getEquipmentId(), "Equipment id", errorMessages);
        NullObjectValidator.validate(request.getImage(), "Image", errorMessages);
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return CreateEquipmentImageValidationException.class;
    }
}
