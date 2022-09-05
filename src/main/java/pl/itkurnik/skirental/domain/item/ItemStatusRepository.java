package pl.itkurnik.skirental.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemStatusRepository extends JpaRepository<ItemStatus, Integer> {
}