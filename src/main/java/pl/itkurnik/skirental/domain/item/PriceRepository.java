package pl.itkurnik.skirental.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.item.Price;

public interface PriceRepository extends JpaRepository<Price, Integer> {
}