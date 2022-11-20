package pl.itkurnik.skirental.domain.rent.exception;

public class RentNotFoundException extends RuntimeException {

    public RentNotFoundException(Integer id) {
        super(String.format("Rent with id %d not found", id));
    }
}
