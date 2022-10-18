package pl.itkurnik.skirental.domain.equipment.exception;

public class EquipmentNotFoundException extends RuntimeException {

    public EquipmentNotFoundException(Integer id) {
        super(String.format("Equipment with id %s not found", id));
    }
}
