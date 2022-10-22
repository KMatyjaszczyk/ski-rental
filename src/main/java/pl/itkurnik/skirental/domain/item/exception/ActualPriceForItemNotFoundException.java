package pl.itkurnik.skirental.domain.item.exception;

public class ActualPriceForItemNotFoundException extends RuntimeException {

    public ActualPriceForItemNotFoundException(Integer itemId) {
        super(String.format("Actual price for item with id %d not found", itemId));
    }
}
