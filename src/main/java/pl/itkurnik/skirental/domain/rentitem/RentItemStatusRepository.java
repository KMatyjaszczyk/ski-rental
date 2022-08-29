package pl.itkurnik.skirental.domain.rentitem;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.rentitem.RentItemStatus;

public interface RentItemStatusRepository extends JpaRepository<RentItemStatus, Integer> {
}