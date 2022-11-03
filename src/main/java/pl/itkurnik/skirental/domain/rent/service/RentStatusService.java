package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.rent.RentStatus;
import pl.itkurnik.skirental.domain.rent.RentStatuses;
import pl.itkurnik.skirental.domain.rent.exception.RentStatusNotFoundException;
import pl.itkurnik.skirental.domain.rent.repository.RentStatusRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentStatusService {
    private final RentStatusRepository rentStatusRepository;

    public RentStatus findById(Integer id) {
        return rentStatusRepository.findById(id)
                .orElseThrow(() -> new RentStatusNotFoundException(id));
    }

    public List<RentStatus> findAll() {
        return rentStatusRepository.findAll();
    }

    public RentStatus getRentedStatus() {
        return findByName(RentStatuses.RENTED.getName());
    }

    public RentStatus getFinishedStatus() {
        return findByName(RentStatuses.FINISHED.getName());
    }

    public RentStatus getPaidStatus() {
        return findByName(RentStatuses.PAID.getName());
    }

    private RentStatus findByName(String name) {
        return rentStatusRepository.findByName(name)
                .orElseThrow(() -> new RentStatusNotFoundException(name));
    }
}
