package pl.itkurnik.skirental.domain.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.payment.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
}