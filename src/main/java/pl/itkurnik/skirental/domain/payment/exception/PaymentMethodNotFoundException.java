package pl.itkurnik.skirental.domain.payment.exception;

import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

public class PaymentMethodNotFoundException extends ObjectNotFoundException {

    public PaymentMethodNotFoundException(Integer id) {
        super(String.format("Payment method with id %s not found", id));
    }
}
