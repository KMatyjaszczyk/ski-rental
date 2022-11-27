package pl.itkurnik.skirental.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.payment.PaymentMethod;
import pl.itkurnik.skirental.domain.payment.dto.CreatePaymentMethodRequest;
import pl.itkurnik.skirental.domain.payment.exception.PaymentMethodNotFoundException;
import pl.itkurnik.skirental.domain.payment.repository.PaymentMethodRepository;
import pl.itkurnik.skirental.domain.payment.validation.CreatePaymentMethodValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;
    private final CreatePaymentMethodValidator createPaymentMethodValidator;

    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethod findById(Integer id) {
        return paymentMethodRepository.findById(id)
                .orElseThrow(() -> new PaymentMethodNotFoundException(id));
    }

    public void create(CreatePaymentMethodRequest request) {
        createPaymentMethodValidator.validate(request);
        createPaymentMethod(request);
    }

    private void createPaymentMethod(CreatePaymentMethodRequest request) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setName(request.getName());
        paymentMethod.setDescription(request.getDescription());

        paymentMethodRepository.save(paymentMethod);
    }

    public void deleteById(Integer id) {
        boolean paymentMethodDoesNotExist = !paymentMethodRepository.existsById(id);

        if (paymentMethodDoesNotExist) {
            return;
        }

        paymentMethodRepository.deleteById(id);
    }
}
