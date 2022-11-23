package pl.itkurnik.skirental.domain.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.rent.RentItem;

import java.util.List;

public interface RentItemRepository extends JpaRepository<RentItem, Integer> {

    List<RentItem> findAllByRent_IdAndItem_IdIn(Integer rentId, List<Integer> itemsIds);
}