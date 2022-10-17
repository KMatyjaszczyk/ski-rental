package pl.itkurnik.skirental.domain.equipment.exception;

public class UpdateManufacturerValidationException extends RuntimeException {

    public UpdateManufacturerValidationException(String details) {
        super(String.format("Manufacturer update validation failed. Details: %s", details));
    }
}
