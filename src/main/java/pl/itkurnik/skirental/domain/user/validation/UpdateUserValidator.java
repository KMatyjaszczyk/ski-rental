package pl.itkurnik.skirental.domain.user.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.user.dto.UpdateUserRequest;
import pl.itkurnik.skirental.domain.user.exception.UpdateUserValidationException;
import pl.itkurnik.skirental.util.ErrorMessagesBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UpdateUserValidator {

    public void validateRequestData(UpdateUserRequest request) {
        List<String> errorMessages = new ArrayList<>();

        if (Objects.isNull(request.getId())) {
             throw new UpdateUserValidationException("User's id is null");
        }

        if (!Objects.isNull(request.getName())) {
            NameValidator.validate(request.getName(), errorMessages);
        }

        if (!Objects.isNull(request.getSurname())) {
            SurnameValidator.validate(request.getSurname(), errorMessages);
        }

        if (!Objects.isNull(request.getPhoneNumber())) {
            PhoneNumberValidator.validate(request.getPhoneNumber(), errorMessages);
        }

        if (!Objects.isNull(request.getEmail())) {
            EmailValidator.validate(request.getEmail(), errorMessages);
        }

        verifyValidationResult(errorMessages);
    }

    private void verifyValidationResult(List<String> errorMessages) {
        if (!errorMessages.isEmpty()) {
            String combinedMessage = ErrorMessagesBuilder.ofMessages(errorMessages);
            throw new UpdateUserValidationException(combinedMessage);
        }
    }
}
