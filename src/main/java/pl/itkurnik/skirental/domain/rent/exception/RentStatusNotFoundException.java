package pl.itkurnik.skirental.domain.rent.exception;

public class RentStatusNotFoundException extends RuntimeException {

    public RentStatusNotFoundException(Integer id) {
        super(String.format("Order status with id %d not found", id));
    }

    public RentStatusNotFoundException(String name) {
        super(String.format("Order status with name %s not found", name));
    }
}
