package pl.itkurnik.skirental.domain.payment.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class CreatePaymentValidationException extends ValidationException {

    public CreatePaymentValidationException(String details) {
        super(String.format("Create payment method validation failed. Details: %s", details));
    }
}
