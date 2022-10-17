package pl.itkurnik.skirental.domain.equipment.exception;

public class CreateManufacturerValidationException extends RuntimeException {

    public CreateManufacturerValidationException(String details) {
        super(String.format("Manufacturer create validation failed. Details: %s", details));
    }
}
