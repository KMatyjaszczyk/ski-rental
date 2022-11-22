package pl.itkurnik.skirental.domain.rent.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rent.dto.CreateRentRequest;
import pl.itkurnik.skirental.domain.rent.exception.CreateRentValidationException;
import pl.itkurnik.skirental.util.validation.EmptyStringValidator;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
class CreateRentFieldsValidator extends MultipleFieldsValidator<CreateRentRequest> {
    @Override
    protected void processFieldsValidation(CreateRentRequest request, List<String> errorMessages) {
        validatePlannedReturnDate(request.getPlannedReturnDate(), errorMessages);
        NullObjectValidator.validate(request.getClientId(), "Client id", errorMessages);
        NullObjectValidator.validate(request.getClientDocumentTypeId(), "Client document type", errorMessages);
        EmptyStringValidator.validate(request.getClientDocumentNumber(), "Client document number", errorMessages);
        NullObjectValidator.validate(request.getEmployeeId(), "Employee id", errorMessages);
        validateRentItemsIds(request.getRentItemsIds(), errorMessages);
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

    private void validateRentItemsIds(List<Integer> rentItemsIds, List<String> errorMessages) {
        if (Objects.isNull(rentItemsIds) || rentItemsIds.isEmpty()) {
            errorMessages.add("Rent items list is empty");
        }

        Set<Integer> uniqueIds = new HashSet<>(rentItemsIds);
        boolean itemsAreDuplicated = rentItemsIds.size() != uniqueIds.size();

        if (itemsAreDuplicated) {
            errorMessages.add("Some items are duplicated");
        }
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return CreateRentValidationException.class;
    }
}
