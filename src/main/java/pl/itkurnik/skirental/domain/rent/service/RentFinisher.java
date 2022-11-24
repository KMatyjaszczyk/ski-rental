package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.rent.Rent;
import pl.itkurnik.skirental.domain.rent.RentItemStatuses;
import pl.itkurnik.skirental.domain.rent.exception.RentNotFoundException;
import pl.itkurnik.skirental.domain.rent.repository.RentRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RentFinisher {
    private final RentRepository rentRepository;
    private final RentStatusService rentStatusService;

    public void finishRentIfNecessary(Integer rentId, Instant finishDate) {
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new RentNotFoundException(rentId));

        if (areAllRentItemsFinished(rent)) {
            finishRent(rent, finishDate);
        }
    }

    private boolean areAllRentItemsFinished(Rent rent) {
        return rent.getRentItems().stream()
                .allMatch(rentItem ->
                        RentItemStatuses.FINISHED.getName().equals(
                                rentItem.getRentItemStatus().getName()));
    }

    private void finishRent(Rent rent, Instant finishDate) {
        rent.setRentStatus(rentStatusService.getFinishedStatus());
        rent.setEndDate(finishDate);

        rentRepository.save(rent);
    }
}
