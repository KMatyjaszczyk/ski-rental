package pl.itkurnik.skirental.domain.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.item.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}