package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.payment.Payment;
import pl.itkurnik.skirental.domain.rent.Rent;
import pl.itkurnik.skirental.domain.rent.repository.RentRepository;

import java.math.BigDecimal;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RentStatusChanger {
    private final RentRepository rentRepository;
    private final RentService rentService;
    private final RentStatusService rentStatusService;

    public void changeToPaidIfNecessary(Integer rentId) {
        Rent rent = rentService.findById(rentId);
        BigDecimal rentTotalCost = rentService.calculateCostForRentById(rentId);
        BigDecimal rentAlreadyCoveredAmount = calculateRentCoveredAmount(rent.getPayments());


        if (rentAlreadyCoveredAmount.compareTo(rentTotalCost) >= 0) {
            changeStatusToPaid(rent);
        }
    }

    private BigDecimal calculateRentCoveredAmount(Set<Payment> rentPayments) {
        return rentPayments.stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void changeStatusToPaid(Rent rent) {
        rent.setRentStatus(rentStatusService.getPaidStatus());
        rentRepository.save(rent);
    }
}
