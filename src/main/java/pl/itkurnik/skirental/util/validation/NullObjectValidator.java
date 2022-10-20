package pl.itkurnik.skirental.util.validation;

import java.util.List;
import java.util.Objects;

public class NullObjectValidator {

    public static void validate(Object validatedField, String fieldName, List<String> errorMessages) {
        if (Objects.isNull(validatedField)) {
            errorMessages.add(String.format("%s is null", fieldName));
        }
    }
}
