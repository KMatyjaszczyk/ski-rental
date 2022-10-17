package pl.itkurnik.skirental.domain.user.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.user.User;
import pl.itkurnik.skirental.domain.user.repository.UserRepository;
import pl.itkurnik.skirental.domain.user.dto.CreateRegisteredUserRequest;
import pl.itkurnik.skirental.domain.user.exception.CreateUserValidationException;
import pl.itkurnik.skirental.domain.user.exception.EmailAlreadyTakenException;
import pl.itkurnik.skirental.domain.user.exception.UserWithPhoneNumberAlreadyRegistered;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateRegisteredUserValidator extends MultipleFieldsValidator<CreateRegisteredUserRequest> {
    private final UserRepository userRepository;

    @Override
    protected void processFieldsValidation(CreateRegisteredUserRequest request, List<String> errorMessages) {
        NameValidator.validate(request.getName(), errorMessages);
        SurnameValidator.validate(request.getSurname(), errorMessages);
        PhoneNumberValidator.validate(request.getPhoneNumber(), errorMessages);
        EmailValidator.validate(request.getEmail(), errorMessages);
        validatePassword(request.getPassword(), errorMessages);
    }

    private void validatePassword(String password, List<String> errorMessages) {
        boolean passwordIsTooShort = password.length() < Constants.MINIMAL_PASSWORD_LENGTH;
        if (passwordIsTooShort) {
            errorMessages.add("Password is too short");
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

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return CreateUserValidationException.class;
    }
}
