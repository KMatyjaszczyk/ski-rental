package pl.itkurnik.skirental.domain.rent.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rent.exception.RentNotFoundException;
import pl.itkurnik.skirental.domain.rent.repository.RentRepository;

@Component
@RequiredArgsConstructor
public class RentValidator {
    private final RentRepository rentRepository;

    public void validateIfRentExistsById(Integer rentId) {
        boolean rentDoesNotExist = !rentRepository.existsById(rentId);

        if (rentDoesNotExist) {
            throw new RentNotFoundException(rentId);
        }
    }
}
