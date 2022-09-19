package pl.itkurnik.skirental.domain.user.validation;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$");

    public static void validate(String email, List<String> errorMessages) {
        if (StringUtils.isBlank(email)) {
            errorMessages.add("User email is blank.");
            return;
        }

        boolean emailMatchesPattern = EMAIL_PATTERN.matcher(email).matches();
        if (!emailMatchesPattern) {
            errorMessages.add("User email is invalid");
        }
    }
}
