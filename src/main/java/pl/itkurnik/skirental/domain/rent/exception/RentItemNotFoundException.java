package pl.itkurnik.skirental.domain.rent.exception;

import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

public class RentItemNotFoundException extends ObjectNotFoundException {

    public RentItemNotFoundException(Integer rentId, Integer itemId) {
        super(String.format("Rent item with rent id %d and item id %d nto found", rentId, itemId));
    }
}
