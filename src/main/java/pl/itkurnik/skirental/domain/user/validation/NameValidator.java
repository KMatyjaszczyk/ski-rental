package pl.itkurnik.skirental.domain.user.validation;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class NameValidator {

    public static void validate(String name, List<String> errorMessages) {
        if (StringUtils.isBlank(name)) {
            errorMessages.add("User name is blank");
        }
    }
}
