package pl.itkurnik.skirental.domain.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.item.ItemStatus;

import java.util.Optional;

public interface ItemStatusRepository extends JpaRepository<ItemStatus, Integer> {

    Optional<ItemStatus> findByName(String name);
}