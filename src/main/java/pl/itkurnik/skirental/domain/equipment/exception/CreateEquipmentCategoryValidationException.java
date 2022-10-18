package pl.itkurnik.skirental.domain.equipment.exception;

public class CreateEquipmentCategoryValidationException extends RuntimeException {

    public CreateEquipmentCategoryValidationException(String details) {
        super(String.format("Equipment category create validation failed. Details: %s", details));
    }
}
