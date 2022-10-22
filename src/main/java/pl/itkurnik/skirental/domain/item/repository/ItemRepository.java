package pl.itkurnik.skirental.domain.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.item.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAllByEquipment_Id(Integer equipmentId);

    List<Item> findAllBySize_Id(Integer sizeId);
}