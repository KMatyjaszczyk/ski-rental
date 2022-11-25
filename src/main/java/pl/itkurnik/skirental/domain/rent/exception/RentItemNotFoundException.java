package pl.itkurnik.skirental.domain.rent.exception;

import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

public class RentItemNotFoundException extends ObjectNotFoundException {

    public RentItemNotFoundException(Integer id) {
        super(String.format("Rent item with id %d not found", id));
    }

    public RentItemNotFoundException(Integer rentId, Integer itemId) {
        super(String.format("Rent item with rent id %d and item id %d not found", rentId, itemId));
    }
}
