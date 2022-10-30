package pl.itkurnik.skirental.domain.equipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.equipment.Size;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Integer> {

    List<Size> findAllByEquipmentCategory_Id(Integer categoryId);

    void deleteAllByEquipmentCategory_Id(Integer categoryId);
}