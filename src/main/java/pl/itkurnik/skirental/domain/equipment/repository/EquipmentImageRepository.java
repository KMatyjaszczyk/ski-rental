package pl.itkurnik.skirental.domain.equipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.equipment.EquipmentImage;

import java.util.List;

public interface EquipmentImageRepository extends JpaRepository<EquipmentImage, Integer> {

    List<EquipmentImage> findAllByEquipment_Id(Integer equipmentId);
}