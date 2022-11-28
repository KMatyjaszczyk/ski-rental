package pl.itkurnik.skirental.domain.rentaldata.exception;

import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

public class RentalDataNotFoundException extends ObjectNotFoundException {

    public RentalDataNotFoundException() {
        super("Rental data not found");
    }
}
