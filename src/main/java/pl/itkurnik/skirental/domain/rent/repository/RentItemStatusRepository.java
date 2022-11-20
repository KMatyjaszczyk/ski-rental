package pl.itkurnik.skirental.domain.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.rent.RentItemStatus;

import java.util.Optional;

public interface RentItemStatusRepository extends JpaRepository<RentItemStatus, Integer> {

    Optional<RentItemStatus> findByName(String name);
}