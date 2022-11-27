package pl.itkurnik.skirental.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.payment.PaymentMethod;
import pl.itkurnik.skirental.domain.payment.dto.CreatePaymentMethodRequest;
import pl.itkurnik.skirental.domain.payment.dto.PaymentMethodInfoDto;
import pl.itkurnik.skirental.domain.payment.exception.CreatePaymentMethodValidationException;
import pl.itkurnik.skirental.domain.payment.exception.PaymentMethodNotFoundException;
import pl.itkurnik.skirental.domain.payment.service.PaymentMethodService;
import pl.itkurnik.skirental.domain.payment.util.PaymentMethodMapper;

import java.util.List;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/payment_method")
@RequiredArgsConstructor
@Slf4j
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<List<PaymentMethodInfoDto>> findAll() {
        try {
            log.info("Receiving all payment methods");
            List<PaymentMethod> paymentMethods = paymentMethodService.findAll();
            log.info("All payment methods received successfully");
            return ResponseEntity.ok(PaymentMethodMapper.mapAllToInfoDto(paymentMethods));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodInfoDto> findById(@PathVariable Integer id) {
        try {
            log.info("Receiving payment method with id {}", id);
            PaymentMethod paymentMethod = paymentMethodService.findById(id);
            log.info("Payment method with id {} received successfully", id);
            return ResponseEntity.ok(PaymentMethodMapper.mapToInfoDto(paymentMethod));
        } catch (PaymentMethodNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreatePaymentMethodRequest request) {
        try {
            log.info("Creating payment method {}", request.getName());
            paymentMethodService.create(request);
            log.info("Payment method {} created successfully", request.getName());
            return ResponseEntity.ok().build();
        } catch (CreatePaymentMethodValidationException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
