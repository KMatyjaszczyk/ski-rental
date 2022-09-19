package pl.itkurnik.skirental.domain.user.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.user.User;
import pl.itkurnik.skirental.domain.user.UserRepository;
import pl.itkurnik.skirental.domain.user.dto.CreateRegisteredUserRequest;
import pl.itkurnik.skirental.domain.user.exception.CreateUserValidationException;
import pl.itkurnik.skirental.domain.user.exception.UserWithPhoneNumberAlreadyRegistered;
import pl.itkurnik.skirental.domain.user.exception.EmailAlreadyTakenException;
import pl.itkurnik.skirental.util.ErrorMessagesBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateRegisteredUserValidator {
    private final UserRepository userRepository;

    public void validateRequestData(CreateRegisteredUserRequest request) {
        List<String> errorMessages = new ArrayList<>();

        NameValidator.validate(request.getName(), errorMessages);
        SurnameValidator.validate(request.getSurname(), errorMessages);
        PhoneNumberValidator.validate(request.getPhoneNumber(), errorMessages);
        EmailValidator.validate(request.getEmail(), errorMessages);
        validatePassword(request.getPassword(), errorMessages);

        verifyValidationResult(errorMessages);
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

    public void validateIfEmailIsAlreadyTaken(String email) {
        Optional<User> userByEmail = userRepository.findByEmail(email);
        if (userByEmail.isPresent()) {
            throw new EmailAlreadyTakenException(email);
        }
    }

    public void validateIfUserWithPhoneNumberIsAlreadyRegistered(String phoneNumber) {
        Optional<User> userByPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);

        userByPhoneNumber.ifPresent(this::validateIfIsRegistered);
    }

    private void validateIfIsRegistered(User user) {
        if (user.getIsRegistered()) {
            throw new UserWithPhoneNumberAlreadyRegistered(user.getPhoneNumber());
        }
    }
}
