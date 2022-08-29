package pl.itkurnik.skirental.domain.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.equipment.EquipmentCategory;

public interface EquipmentCategoryRepository extends JpaRepository<EquipmentCategory, Integer> {
}