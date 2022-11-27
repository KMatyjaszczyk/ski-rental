package pl.itkurnik.skirental.domain.payment.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class CreatePaymentMethodValidationException extends ValidationException {

    public CreatePaymentMethodValidationException(String details) {
        super(String.format("Create payment method validation failed. Details: %s", details));
    }
}
