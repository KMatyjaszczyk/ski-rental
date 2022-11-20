package pl.itkurnik.skirental.domain.rent.exception;

public class RentItemStatusNotFoundException extends RuntimeException {

    public RentItemStatusNotFoundException(Integer id) {
        super(String.format("Rent item status with id %d not found", id));
    }

    public RentItemStatusNotFoundException(String name) {
        super(String.format("Rent item status with name %s not found", name));
    }
}
