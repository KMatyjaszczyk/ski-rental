package pl.itkurnik.skirental.domain.rent.validation.create;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rent.dto.CreateRentRequest;
import pl.itkurnik.skirental.domain.rent.exception.CreateRentValidationException;
import pl.itkurnik.skirental.util.validation.ItemsIdsListValidator;
import pl.itkurnik.skirental.util.validation.EmptyStringValidator;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
class CreateRentFieldsValidator extends MultipleFieldsValidator<CreateRentRequest> {
    @Override
    protected void processFieldsValidation(CreateRentRequest request, List<String> errorMessages) {
        validatePlannedReturnDate(request.getPlannedReturnDate(), errorMessages);
        NullObjectValidator.validate(request.getClientId(), "Client id", errorMessages);
        NullObjectValidator.validate(request.getClientDocumentTypeId(), "Client document type", errorMessages);
        EmptyStringValidator.validate(request.getClientDocumentNumber(), "Client document number", errorMessages);
        NullObjectValidator.validate(request.getEmployeeId(), "Employee id", errorMessages);
        ItemsIdsListValidator.validate(request.getRentItemsIds(), errorMessages);
    }

    private void validatePlannedReturnDate(LocalDate plannedReturnDate, List<String> errorMessages) {
        if (Objects.isNull(plannedReturnDate)) {
            errorMessages.add("Planned return date is null");
            return;
        }

        if (plannedReturnDate.isBefore(LocalDate.now())) {
            errorMessages.add("Planned return date is from the past");
        }
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return CreateRentValidationException.class;
    }
}
