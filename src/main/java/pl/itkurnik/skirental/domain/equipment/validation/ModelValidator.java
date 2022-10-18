package pl.itkurnik.skirental.domain.equipment.validation;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

class ModelValidator {

    public static void validate(String model, List<String> errorMessages) {
        if (StringUtils.isBlank(model)) {
            errorMessages.add("Model is empty");
        }
    }
}
