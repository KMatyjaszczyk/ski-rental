package pl.itkurnik.skirental.domain.equipment.exception;

public class EquipmentImageNotFoundException extends RuntimeException {

    public EquipmentImageNotFoundException(Integer id) {
        super(String.format("Equipment image with id %s not found", id));
    }
}
