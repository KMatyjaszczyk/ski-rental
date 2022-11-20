package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.rent.RentItemStatus;
import pl.itkurnik.skirental.domain.rent.RentItemStatuses;
import pl.itkurnik.skirental.domain.rent.exception.RentItemStatusNotFoundException;
import pl.itkurnik.skirental.domain.rent.repository.RentItemStatusRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentItemStatusService {
    private final RentItemStatusRepository rentItemStatusRepository;

    public RentItemStatus findById(Integer id) {
        return rentItemStatusRepository.findById(id)
                .orElseThrow(() -> new RentItemStatusNotFoundException(id));
    }

    public List<RentItemStatus> findAll() {
        return rentItemStatusRepository.findAll();
    }

    private RentItemStatus getRentedStatus() {
        return findByName(RentItemStatuses.RENTED.getName());
    }

    private RentItemStatus getFinishedStatus() {
        return findByName(RentItemStatuses.FINISHED.getName());
    }

    private RentItemStatus findByName(String name) {
        return rentItemStatusRepository.findByName(name)
                .orElseThrow(() -> new RentItemStatusNotFoundException(name));
    }
}
