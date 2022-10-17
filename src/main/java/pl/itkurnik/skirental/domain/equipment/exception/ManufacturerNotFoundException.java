package pl.itkurnik.skirental.domain.equipment.exception;

public class ManufacturerNotFoundException extends RuntimeException {

    public ManufacturerNotFoundException(Integer id) {
        super(String.format("Manufacturer with id %s not found", id));
    }
}
