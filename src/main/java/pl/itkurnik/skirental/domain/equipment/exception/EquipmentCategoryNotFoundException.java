package pl.itkurnik.skirental.domain.equipment.exception;

public class EquipmentCategoryNotFoundException extends RuntimeException {

    public EquipmentCategoryNotFoundException(Integer id) {
        super(String.format("Equipment category with id %s not found", id));
    }
}
