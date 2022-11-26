package pl.itkurnik.skirental.domain.item.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.item.dto.CalculateItemsRentCostRequest;
import pl.itkurnik.skirental.domain.item.exception.CalculateItemsRentCostValidationException;
import pl.itkurnik.skirental.util.validation.ItemsIdsListValidator;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
public class CalculateItemsRentCostFieldsValidator extends MultipleFieldsValidator<CalculateItemsRentCostRequest> {

    @Override
    protected void processFieldsValidation(CalculateItemsRentCostRequest request, List<String> errorMessages) {
        ItemsIdsListValidator.validate(request.getItemsIds(), errorMessages);
        validateDates(request, errorMessages);
    }

    private void validateDates(CalculateItemsRentCostRequest request, List<String> errorMessages) {
        LocalDate dateFrom = request.getDateFrom();
        LocalDate dateTo = request.getDateTo();

        if (Objects.isNull(dateFrom)) {
            errorMessages.add("Date from is empty");
            return;
        }

        if (Objects.isNull(dateTo)) {
            errorMessages.add("Date to is empty");
            return;
        }

        if (dateFrom.isAfter(dateTo)) {
            errorMessages.add("Date from is before rent end date");
        }
    }


    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return CalculateItemsRentCostValidationException.class;
    }
}
