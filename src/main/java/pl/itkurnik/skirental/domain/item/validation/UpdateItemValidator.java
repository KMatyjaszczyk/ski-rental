package pl.itkurnik.skirental.domain.item.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.item.dto.UpdateItemRequest;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NegativeBigDecimalValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;

@Component
public class UpdateItemValidator extends MultipleFieldsValidator<UpdateItemRequest> {
    @Override
    protected void processFieldsValidation(UpdateItemRequest request, List<String> errorMessages) {
        NullObjectValidator.validate(request.getId(), "Item id", errorMessages);
        NullObjectValidator.validate(request.getSizeId(), "Size id", errorMessages);
        NullObjectValidator.validate(request.getItemStatusId(), "Item status id", errorMessages);
        NullObjectValidator.validate(request.getLastServiceDate(), "Last service date", errorMessages);
        NegativeBigDecimalValidator.validate(request.getPurchasePrice(), "Purchase price", errorMessages);
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return null;
    }
}
