package pl.itkurnik.skirental.domain.equipment.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class CreateEquipmentValidationException extends ValidationException {

    public CreateEquipmentValidationException(String details) {
        super(String.format("Equipment create validation failed. Details: %s", details));
    }
}
