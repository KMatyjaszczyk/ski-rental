package pl.itkurnik.skirental.domain.rent.validation.returnn;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rent.dto.ReturnRentItemRequest;
import pl.itkurnik.skirental.domain.rent.exception.ReturnRentItemsValidationException;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;

@Component
class ReturnRentItemsFieldsValidator extends MultipleFieldsValidator<ReturnRentItemRequest> {

    @Override
    protected void processFieldsValidation(ReturnRentItemRequest request, List<String> errorMessages) {
        NullObjectValidator.validate(request.getRentId(), "Rent id", errorMessages);
        NullObjectValidator.validate(request.getItemId(), "Item id", errorMessages);
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return ReturnRentItemsValidationException.class;
    }
}
