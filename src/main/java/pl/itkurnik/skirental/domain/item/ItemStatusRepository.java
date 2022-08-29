package pl.itkurnik.skirental.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.item.ItemStatus;

public interface ItemStatusRepository extends JpaRepository<ItemStatus, Integer> {
}