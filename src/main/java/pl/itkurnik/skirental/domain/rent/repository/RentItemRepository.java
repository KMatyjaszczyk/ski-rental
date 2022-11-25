package pl.itkurnik.skirental.domain.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.rent.RentItem;

import java.util.List;
import java.util.Optional;

public interface RentItemRepository extends JpaRepository<RentItem, Integer> {

    List<RentItem> findAllByRent_Id(Integer rentId);

    Optional<RentItem> findByRent_IdAndItem_Id(Integer rentId, Integer itemId);
}