package pl.itkurnik.skirental.domain.rent.exception;

import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

public class RentStatusNotFoundException extends ObjectNotFoundException {

    public RentStatusNotFoundException(Integer id) {
        super(String.format("Order status with id %d not found", id));
    }

    public RentStatusNotFoundException(String name) {
        super(String.format("Order status with name %s not found", name));
    }
}
