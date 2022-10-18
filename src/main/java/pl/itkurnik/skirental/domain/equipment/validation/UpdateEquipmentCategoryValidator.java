package pl.itkurnik.skirental.domain.equipment.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.equipment.dto.UpdateEquipmentCategoryRequest;
import pl.itkurnik.skirental.domain.equipment.exception.UpdateEquipmentCategoryValidationException;

import java.util.Objects;

@Component
public class UpdateEquipmentCategoryValidator {

    public void validate(UpdateEquipmentCategoryRequest request) {
        if (Objects.isNull(request.getId())) {
            throw new UpdateEquipmentCategoryValidationException("Equipment category's id is null");
        }

        if (StringUtils.isBlank(request.getName())) {
            throw new UpdateEquipmentCategoryValidationException("Name is empty");
        }
    }
}
