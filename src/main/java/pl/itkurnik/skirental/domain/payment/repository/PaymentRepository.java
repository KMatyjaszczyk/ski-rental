package pl.itkurnik.skirental.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.payment.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}