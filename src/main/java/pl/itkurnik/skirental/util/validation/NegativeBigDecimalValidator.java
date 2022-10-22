package pl.itkurnik.skirental.util.validation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class NegativeBigDecimalValidator {

    public static void validate(BigDecimal value, String fieldName, List<String> errorMessages) {
        if (Objects.isNull(value)) {
            errorMessages.add(String.format("%s is null", fieldName));
            return;
        }

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            errorMessages.add(String.format("%s is less than zero", fieldName));
        }
    }
}
