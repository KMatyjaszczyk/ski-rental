package pl.itkurnik.skirental.domain.rentitem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RentItemRepository extends JpaRepository<RentItem, Integer> {
}