package pl.itkurnik.skirental.domain.user.validation;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SurnameValidator {

    public static void validate(String surname, List<String> errorMessages) {
        if (StringUtils.isBlank(surname)) {
            errorMessages.add("User surname is blank");
        }
    }
}
