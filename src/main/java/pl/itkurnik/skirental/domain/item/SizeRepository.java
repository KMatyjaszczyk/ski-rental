package pl.itkurnik.skirental.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.item.Size;

public interface SizeRepository extends JpaRepository<Size, Integer> {
}