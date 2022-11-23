package pl.itkurnik.skirental.domain.rent.validation.returnn;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rent.dto.ReturnRentItemsRequest;
import pl.itkurnik.skirental.domain.rent.exception.ReturnRentItemsValidationException;
import pl.itkurnik.skirental.domain.rent.validation.RentItemsIdsListValidator;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;

@Component
class ReturnRentItemsFieldsValidator extends MultipleFieldsValidator<ReturnRentItemsRequest> {

    @Override
    protected void processFieldsValidation(ReturnRentItemsRequest request, List<String> errorMessages) {
        NullObjectValidator.validate(request.getRentId(), "Rent id", errorMessages);
        RentItemsIdsListValidator.validate(request.getItemsIds(), errorMessages);
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return ReturnRentItemsValidationException.class;
    }
}
