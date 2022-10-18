package pl.itkurnik.skirental.domain.equipment.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.equipment.dto.CreateEquipmentCategoryRequest;
import pl.itkurnik.skirental.domain.equipment.exception.CreateEquipmentCategoryValidationException;

@Component
public class CreateEquipmentCategoryValidator {

    public void validate(CreateEquipmentCategoryRequest request) {
        if (StringUtils.isBlank(request.getName())) {
            throw new CreateEquipmentCategoryValidationException("Name is empty");
        }
    }
}
