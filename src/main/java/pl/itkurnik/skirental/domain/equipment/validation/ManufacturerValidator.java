package pl.itkurnik.skirental.domain.equipment.validation;

import java.util.List;
import java.util.Objects;

class ManufacturerValidator {

    public static void validateId(Integer id, List<String> errorMessages) {
        if (Objects.isNull(id)) {
            errorMessages.add("Manufacturer id is null");
        }
    }
}
