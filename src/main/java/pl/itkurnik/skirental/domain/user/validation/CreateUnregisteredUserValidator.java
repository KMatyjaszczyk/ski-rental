package pl.itkurnik.skirental.domain.user.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.user.User;
import pl.itkurnik.skirental.domain.user.repository.UserRepository;
import pl.itkurnik.skirental.domain.user.dto.CreateUnregisteredUserRequest;
import pl.itkurnik.skirental.domain.user.exception.CreateUserValidationException;
import pl.itkurnik.skirental.domain.user.exception.PhoneNumberAlreadyTakenException;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateUnregisteredUserValidator extends MultipleFieldsValidator<CreateUnregisteredUserRequest> {
    private final UserRepository userRepository;

    @Override
    protected void processFieldsValidation(CreateUnregisteredUserRequest request, List<String> errorMessages) {
        NameValidator.validate(request.getName(), errorMessages);
        SurnameValidator.validate(request.getSurname(), errorMessages);
        PhoneNumberValidator.validate(request.getPhoneNumber(), errorMessages);
    }

    public void validateIfPhoneNumberIsAlreadyTaken(String phoneNumber) {
        Optional<User> userByPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        if (userByPhoneNumber.isPresent()) {
            throw new PhoneNumberAlreadyTakenException(phoneNumber);
        }
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return CreateUserValidationException.class;
    }
}
