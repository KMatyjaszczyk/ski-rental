package pl.itkurnik.skirental.domain.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.rent.RentItemStatus;

public interface RentItemStatusRepository extends JpaRepository<RentItemStatus, Integer> {
}