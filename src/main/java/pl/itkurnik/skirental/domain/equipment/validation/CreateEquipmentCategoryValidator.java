package pl.itkurnik.skirental.domain.equipment.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.equipment.dto.CreateEquipmentCategoryRequest;
import pl.itkurnik.skirental.domain.equipment.exception.CreateEquipmentCategoryValidationException;

import java.util.Objects;

@Component
public class CreateEquipmentCategoryValidator {

    public void validate(CreateEquipmentCategoryRequest request) {
        if (StringUtils.isBlank(request.getName())) {
            throw new CreateEquipmentCategoryValidationException("Name is empty");
        }

        if (Objects.isNull(request.getSizes()) || request.getSizes().isEmpty()) {
            throw new CreateEquipmentCategoryValidationException("There is no size provided");
        }

        boolean atLeastOneSizeIsEmpty = request.getSizes().stream()
                .anyMatch(size -> StringUtils.isBlank(size.getSize()));
        if (atLeastOneSizeIsEmpty) {
            throw new CreateEquipmentCategoryValidationException("At least one size is empty");
        }
    }
}
