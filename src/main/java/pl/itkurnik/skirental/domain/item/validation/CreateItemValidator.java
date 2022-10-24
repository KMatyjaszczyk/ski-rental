package pl.itkurnik.skirental.domain.item.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.item.dto.CreateItemRequest;
import pl.itkurnik.skirental.domain.item.dto.PriceForCreateItemRequest;
import pl.itkurnik.skirental.domain.item.exception.CreateItemValidationException;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NegativeBigDecimalValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;
import java.util.Objects;

@Component
public class CreateItemValidator extends MultipleFieldsValidator<CreateItemRequest> {
    @Override
    protected void processFieldsValidation(CreateItemRequest request, List<String> errorMessages) {
        NullObjectValidator.validate(request.getEquipmentId(), "Equipment id", errorMessages);
        NullObjectValidator.validate(request.getSizeId(), "Size id", errorMessages);
        NegativeBigDecimalValidator.validate(request.getPurchasePrice(), "Purchase price", errorMessages);
        validateDefaultPrice(request, errorMessages);
    }

    private void validateDefaultPrice(CreateItemRequest request, List<String> errorMessages) {
        PriceForCreateItemRequest defaultPriceFromRequest = request.getDefaultPrice();

        if (Objects.isNull(defaultPriceFromRequest)) {
            errorMessages.add("Default price is null");
            return;
        }

        NegativeBigDecimalValidator.validate(defaultPriceFromRequest.getPriceValue(), "Default price's value", errorMessages);
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return CreateItemValidationException.class;
    }
}
