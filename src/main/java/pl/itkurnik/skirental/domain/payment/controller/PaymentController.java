package pl.itkurnik.skirental.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.payment.Payment;
import pl.itkurnik.skirental.domain.payment.dto.CreatePaymentRequest;
import pl.itkurnik.skirental.domain.payment.dto.PaymentInfoDto;
import pl.itkurnik.skirental.domain.payment.exception.PaymentNotFoundException;
import pl.itkurnik.skirental.domain.payment.service.PaymentService;
import pl.itkurnik.skirental.domain.payment.util.PaymentMapper;

import java.util.List;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentInfoDto> findById(@PathVariable Integer id) {
        try {
            log.info("Receiving payment with id {}", id);
            Payment payment = paymentService.findById(id);
            log.info("Payment with id {} received successfully", id);
            return ResponseEntity.ok(PaymentMapper.mapToInfoDto(payment));
        } catch (PaymentNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/rent/{rentId}")
    public ResponseEntity<List<PaymentInfoDto>> findAllByRentId(@PathVariable Integer rentId) {
        try {
            log.info("Receiving all payments for rent with id {}", rentId);
            List<Payment> payments = paymentService.findAllByRentId(rentId);
            log.info("All payments for rent with id {} received successfully", rentId);
            return ResponseEntity.ok(PaymentMapper.mapAllToInfoDto(payments));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreatePaymentRequest request) {
        try {
            log.info("Creating payment for rent with id {}", request.getRentId());
            paymentService.create(request);
            log.info("Payment for rent with id {} created successfully", request.getRentId());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e); // TODO proper handling here
        }
    }
}
