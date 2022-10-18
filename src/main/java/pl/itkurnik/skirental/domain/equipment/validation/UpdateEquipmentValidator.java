package pl.itkurnik.skirental.domain.equipment.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.equipment.dto.UpdateEquipmentRequest;
import pl.itkurnik.skirental.domain.equipment.exception.UpdateEquipmentValidationException;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;
import java.util.Objects;

@Component
public class UpdateEquipmentValidator extends MultipleFieldsValidator<UpdateEquipmentRequest> {
    @Override
    protected void processFieldsValidation(UpdateEquipmentRequest request, List<String> errorMessages) {
        if (Objects.isNull(request.getId())) {
            throw new UpdateEquipmentValidationException("User id is null");
        }

        ModelValidator.validate(request.getModel(), errorMessages);
        ManufacturerValidator.validateId(request.getManufacturerId(), errorMessages);
        EquipmentCategoryIdValidator.validate(request.getEquipmentCategoryId(), errorMessages);
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return UpdateEquipmentValidationException.class;
    }
}
