package pl.itkurnik.skirental.domain.rent.exception;

import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

public class ItemsForRentNotFoundException extends ObjectNotFoundException {

    public ItemsForRentNotFoundException() {
        super("Not all items from request were found");
    }
}
