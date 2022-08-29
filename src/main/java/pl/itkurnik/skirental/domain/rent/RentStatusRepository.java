package pl.itkurnik.skirental.domain.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.rent.RentStatus;

public interface RentStatusRepository extends JpaRepository<RentStatus, Integer> {
}