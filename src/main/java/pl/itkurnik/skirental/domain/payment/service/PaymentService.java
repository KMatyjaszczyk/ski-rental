package pl.itkurnik.skirental.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.payment.Payment;
import pl.itkurnik.skirental.domain.payment.PaymentMethod;
import pl.itkurnik.skirental.domain.payment.dto.CreatePaymentRequest;
import pl.itkurnik.skirental.domain.payment.repository.PaymentRepository;
import pl.itkurnik.skirental.domain.payment.validation.CreatePaymentFieldsValidator;
import pl.itkurnik.skirental.domain.payment.validation.PaymentValidator;
import pl.itkurnik.skirental.domain.rent.Rent;
import pl.itkurnik.skirental.domain.rent.exception.RentNotFoundException;
import pl.itkurnik.skirental.domain.rent.service.RentService;
import pl.itkurnik.skirental.domain.rent.service.RentStatusChanger;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final RentService rentService;
    private final PaymentMethodService paymentMethodService;
    private final RentStatusChanger rentStatusChanger;
    private final CreatePaymentFieldsValidator createPaymentFieldsValidator;
    private final PaymentValidator paymentValidator;

    public Payment findById(Integer id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
    }

    public List<Payment> findAllByRentId(Integer rentId) {
        return paymentRepository.findAllByRent_Id(rentId);
    }

    @Transactional
    public void create(CreatePaymentRequest request) {
        validateCreate(request);

        createPayment(request);
        rentStatusChanger.changeToPaidIfNecessary(request.getRentId());
    }

    private void validateCreate(CreatePaymentRequest request) {
        createPaymentFieldsValidator.validateFields(request);
        paymentValidator.validateCreate(request);
    }

    private void createPayment(CreatePaymentRequest request) {
        Rent rent = rentService.findById(request.getRentId());
        PaymentMethod paymentMethod = paymentMethodService.findById(request.getPaymentMethodId());

        Payment payment = new Payment();
        payment.setRent(rent);
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(paymentMethod);
        payment.setRealisationDate(Instant.now());

        paymentRepository.saveAndFlush(payment);
    }
}
