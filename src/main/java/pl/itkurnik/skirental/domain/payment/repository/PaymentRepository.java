package pl.itkurnik.skirental.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.payment.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findAllByRent_Id(Integer rentId);
}