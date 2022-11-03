package pl.itkurnik.skirental.domain.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.rent.RentStatus;

import java.util.Optional;

public interface RentStatusRepository extends JpaRepository<RentStatus, Integer> {

    Optional<RentStatus> findByName(String name);
}