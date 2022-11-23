package pl.itkurnik.skirental.domain.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.rent.RentItem;

import java.util.Optional;

public interface RentItemRepository extends JpaRepository<RentItem, Integer> {

    Optional<RentItem> findByRent_IdAndItem_Id(Integer rentId, Integer itemId);
}