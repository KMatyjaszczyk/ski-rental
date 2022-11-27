package pl.itkurnik.skirental.domain.rent.exception;

import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

public class RentNotFoundException extends ObjectNotFoundException {

    public RentNotFoundException(Integer id) {
        super(String.format("Rent with id %d not found", id));
    }
}
