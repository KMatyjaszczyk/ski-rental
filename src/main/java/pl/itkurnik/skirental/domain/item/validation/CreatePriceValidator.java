package pl.itkurnik.skirental.domain.item.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.item.dto.CreatePriceRequest;
import pl.itkurnik.skirental.domain.item.exception.CreatePriceValidationException;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NegativeBigDecimalValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;

@Component
public class CreatePriceValidator extends MultipleFieldsValidator<CreatePriceRequest> {

    @Override
    protected void processFieldsValidation(CreatePriceRequest request, List<String> errorMessages) {
        NullObjectValidator.validate(request.getItemId(), "Item id", errorMessages);
        NegativeBigDecimalValidator.validate(request.getPriceValue(), "Price value", errorMessages);
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return CreatePriceValidationException.class;
    }
}
