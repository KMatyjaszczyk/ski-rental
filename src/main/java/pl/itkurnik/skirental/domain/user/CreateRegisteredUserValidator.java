package pl.itkurnik.skirental.domain.user;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.user.dto.CreateRegisteredUserRequest;
import pl.itkurnik.skirental.domain.user.exception.CreateUserValidationException;
import pl.itkurnik.skirental.domain.user.exception.PhoneNumberNotUniqueException;
import pl.itkurnik.skirental.domain.user.exception.UserAlreadyExistsException;
import pl.itkurnik.skirental.util.ErrorMessagesBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class CreateRegisteredUserValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$");

    private final UserRepository userRepository;

    public void validateRequestData(CreateRegisteredUserRequest request) {
        List<String> errorMessages = new ArrayList<>();

        validateName(request.getName(), errorMessages);
        validateSurname(request.getSurname(), errorMessages);
        validatePhoneNumber(request.getPhoneNumber(), errorMessages);
        validateEmail(request.getEmail(), errorMessages);
        validatePassword(request.getPassword(), errorMessages);

        verifyValidationResult(errorMessages);
    }

    private void validateName(String name, List<String> errorMessages) {
        if (StringUtils.isBlank(name)) {
            errorMessages.add("User name is blank");
        }
    }

    private void validateSurname(String surname, List<String> errorMessages) {
        if (StringUtils.isBlank(surname)) {
            errorMessages.add("User surname is blank");
        }
    }

    private void validatePhoneNumber(String phoneNumber, List<String> errorMessages) {
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

    private void validateEmail(String email, List<String> errorMessages) {
        if (StringUtils.isBlank(email)) {
            errorMessages.add("User email is blank.");
            return;
        }

        boolean emailMatchesPattern = EMAIL_PATTERN.matcher(email).matches();
        if (!emailMatchesPattern) {
            errorMessages.add("User email is invalid");
        }
    }

    private void validatePassword(String password, List<String> errorMessages) {
        boolean passwordIsTooShort = password.length() < Constants.MINIMAL_PASSWORD_LENGTH;
        if (passwordIsTooShort) {
            errorMessages.add("Password is too short");
        }
    }

    private void verifyValidationResult(List<String> errorMessages) {
        if (!errorMessages.isEmpty()) {
            String combinedMessage = ErrorMessagesBuilder.ofMessages(errorMessages);
            throw new CreateUserValidationException(combinedMessage);
        }
    }

    public void validateIfUserExists(String email) {
        Optional<User> userByEmail = userRepository.findByEmail(email);
        if (userByEmail.isPresent()) {
            throw new UserAlreadyExistsException(email);
        }
    }

    public void validatePhoneNumberUniqueness(String phoneNumber) {
        Optional<User> userByPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        if (userByPhoneNumber.isPresent()) {
            throw new PhoneNumberNotUniqueException(phoneNumber);
        }
    }
}
