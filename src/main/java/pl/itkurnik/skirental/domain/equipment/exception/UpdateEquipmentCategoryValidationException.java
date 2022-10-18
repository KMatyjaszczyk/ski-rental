package pl.itkurnik.skirental.domain.equipment.exception;

public class UpdateEquipmentCategoryValidationException extends RuntimeException {

    public UpdateEquipmentCategoryValidationException(String details) {
        super(String.format("Equipment category update validation failed. Details: %s", details));
    }
}
