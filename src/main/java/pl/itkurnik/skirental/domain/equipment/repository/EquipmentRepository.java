package pl.itkurnik.skirental.domain.equipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.equipment.Equipment;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    List<Equipment> findAllByManufacturer_Id(Integer manufacturerId);

    List<Equipment> findAllByEquipmentCategory_Id(Integer categoryId);
}