package pl.itkurnik.skirental.domain.item.exception;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(Integer id) {
        super(String.format("Price with id %d not found", id));
    }
}
