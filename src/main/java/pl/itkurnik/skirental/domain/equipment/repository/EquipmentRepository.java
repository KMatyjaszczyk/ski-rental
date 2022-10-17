package pl.itkurnik.skirental.domain.equipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.equipment.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
}