package pl.itkurnik.skirental.domain.equipment.validation;

import java.util.List;
import java.util.Objects;

public class EquipmentCategoryIdValidator {

    public static void validate(Integer id, List<String> errorMessages) {
        if (Objects.isNull(id)) {
            errorMessages.add("Equipment category id is null");
        }
    }
}
