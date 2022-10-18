package pl.itkurnik.skirental.domain.equipment.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class UpdateEquipmentValidationException extends ValidationException {

    public UpdateEquipmentValidationException(String details) {
        super(String.format("Equipment update validation failed. Details: %s", details));
    }
}
