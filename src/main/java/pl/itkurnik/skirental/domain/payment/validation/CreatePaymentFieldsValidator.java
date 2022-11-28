package pl.itkurnik.skirental.domain.payment.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.payment.dto.CreatePaymentRequest;
import pl.itkurnik.skirental.domain.payment.exception.CreatePaymentValidationException;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NegativeBigDecimalValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;

@Component
public class CreatePaymentFieldsValidator extends MultipleFieldsValidator<CreatePaymentRequest> {

    @Override
    protected void processFieldsValidation(CreatePaymentRequest request, List<String> errorMessages) {
        NullObjectValidator.validate(request.getRentId(), "Rent id", errorMessages);
        NegativeBigDecimalValidator.validate(request.getAmount(), "Payment amount", errorMessages);
        NullObjectValidator.validate(request.getPaymentMethodId(), "Payment method id", errorMessages);
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return CreatePaymentValidationException.class;
    }
}
