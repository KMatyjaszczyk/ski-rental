package pl.itkurnik.skirental.domain.user.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.user.User;
import pl.itkurnik.skirental.domain.user.UserRepository;
import pl.itkurnik.skirental.domain.user.dto.CreateUnregisteredUserRequest;
import pl.itkurnik.skirental.domain.user.exception.CreateUserValidationException;
import pl.itkurnik.skirental.domain.user.exception.PhoneNumberAlreadyTakenException;
import pl.itkurnik.skirental.util.ErrorMessagesBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateUnregisteredUserValidator { // TODO KM make this class abstract
    private final UserRepository userRepository;

    public void validateRequestData(CreateUnregisteredUserRequest request) {
        List<String> errorMessages = new ArrayList<>();

        NameValidator.validate(request.getName(), errorMessages);
        SurnameValidator.validate(request.getSurname(), errorMessages);
        PhoneNumberValidator.validate(request.getPhoneNumber(), errorMessages);

        verifyValidationResult(errorMessages);
    }

    private void verifyValidationResult(List<String> errorMessages) {
        if (!errorMessages.isEmpty()) {
            String combinedMessage = ErrorMessagesBuilder.ofMessages(errorMessages);
            throw new CreateUserValidationException(combinedMessage);
        }
    }

    public void validateIfPhoneNumberIsAlreadyTaken(String phoneNumber) {
        Optional<User> userByPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        if (userByPhoneNumber.isPresent()) {
            throw new PhoneNumberAlreadyTakenException(phoneNumber);
        }
    }
}
