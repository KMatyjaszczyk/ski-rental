package pl.itkurnik.skirental.domain.equipment.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class CreateEquipmentImageValidationException extends ValidationException {

    public CreateEquipmentImageValidationException(String details) {
        super(String.format("Equipment image create validation failed. Details: %s", details));
    }
}
