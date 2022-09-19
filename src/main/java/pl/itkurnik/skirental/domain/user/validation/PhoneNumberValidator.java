package pl.itkurnik.skirental.domain.user.validation;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import pl.itkurnik.skirental.api.Constants;

import java.util.List;

public class PhoneNumberValidator {

    public static void validate(String phoneNumber, List<String> errorMessages) {
        if (StringUtils.isBlank(phoneNumber)) {
            errorMessages.add("User phone number is blank");
            return;
        }

        boolean phoneNumberLengthIsInProperRange = Range.between(Constants.PHONE_NUMBER_LENGTH,
                        Constants.PHONE_NUMBER_WITH_PREFIX_LENGTH)
                .contains(phoneNumber.length());
        if (!phoneNumberLengthIsInProperRange) {
            errorMessages.add("User phone number length is not proper");
        }
    }
}
