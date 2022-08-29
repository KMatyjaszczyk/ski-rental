package pl.itkurnik.skirental.domain.rentitem;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.rentitem.RentItem;

public interface RentItemRepository extends JpaRepository<RentItem, Integer> {
}