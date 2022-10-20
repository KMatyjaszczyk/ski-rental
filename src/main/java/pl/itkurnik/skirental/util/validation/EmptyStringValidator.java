package pl.itkurnik.skirental.util.validation;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class EmptyStringValidator {

    public static void validate(String validatedField, String fieldName, List<String> errorMessages) {
        if (StringUtils.isEmpty(validatedField)) {
            errorMessages.add(String.format("%s is empty", fieldName));
        }
    }
}
