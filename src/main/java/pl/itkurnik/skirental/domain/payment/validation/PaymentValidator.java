package pl.itkurnik.skirental.domain.payment.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.payment.dto.CreatePaymentRequest;
import pl.itkurnik.skirental.domain.rent.Rent;
import pl.itkurnik.skirental.domain.rent.RentStatuses;
import pl.itkurnik.skirental.domain.rent.service.RentService;

@Component
@RequiredArgsConstructor
public class PaymentValidator {
    private final RentService rentService;

    public void validateCreate(CreatePaymentRequest request) {
        validateIfRentIsFinished(request.getRentId());
    }

    private void validateIfRentIsFinished(Integer rentId) {
        Rent rent = rentService.findById(rentId);

        boolean rentIsNotInFinishedState = !rent.getRentStatus().getName().equals(
                RentStatuses.FINISHED.getName());

        if (rentIsNotInFinishedState) {
            throw new IllegalStateException(String.format("Rent with id %d is not finished", rentId));
        }
    }
}
