package pl.itkurnik.skirental.domain.rent.exception;

public class ItemsForRentNotFoundException extends RuntimeException {

    public ItemsForRentNotFoundException() {
        super("Not all items from request were found");
    }
}
