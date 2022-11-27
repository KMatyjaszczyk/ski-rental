package pl.itkurnik.skirental.domain.rent.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rent.Rent;
import pl.itkurnik.skirental.domain.rent.RentStatuses;
import pl.itkurnik.skirental.domain.rent.exception.RentNotFoundException;
import pl.itkurnik.skirental.domain.rent.repository.RentRepository;

@Component
@RequiredArgsConstructor
public class RentValidator {
    private final RentRepository rentRepository;

    public void validateIfRentIsInRentedState(Integer rentId) {
        validateIfRentIsInState(rentId, RentStatuses.RENTED);
    }

    private void validateIfRentIsInState(Integer rentId, RentStatuses stateToValidate) {
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new RentNotFoundException(rentId));

        if (!stateToValidate.getName().equals(rent.getRentStatus().getName())) {
            throw new IllegalStateException(String.format(
                    "Rent with id %d is not in %s state", rentId, stateToValidate.getName()));
        }
    }

    public void validateIfRentIsInFinishedState(Integer rentId) {
        validateIfRentIsInState(rentId, RentStatuses.FINISHED);
    }
}
