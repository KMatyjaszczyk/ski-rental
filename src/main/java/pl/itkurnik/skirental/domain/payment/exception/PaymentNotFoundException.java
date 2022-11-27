package pl.itkurnik.skirental.domain.payment.exception;

import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

public class PaymentNotFoundException extends ObjectNotFoundException {

    public PaymentNotFoundException(Integer id) {
        super(String.format("Payment with id %d not found", id));
    }
}
