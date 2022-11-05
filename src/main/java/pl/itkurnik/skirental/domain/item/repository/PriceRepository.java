package pl.itkurnik.skirental.domain.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.item.Price;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Integer> {

    List<Price> findAllByItem_Id(Integer itemId);

    void deleteAllByItem_Id(Integer itemId);
}