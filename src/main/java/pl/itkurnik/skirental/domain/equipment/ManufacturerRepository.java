package pl.itkurnik.skirental.domain.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.equipment.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
}