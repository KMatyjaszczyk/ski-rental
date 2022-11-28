package pl.itkurnik.skirental.domain.rentaldata.exception;

import pl.itkurnik.skirental.util.validation.ValidationException;

public class UpdateRentalDataValidationException extends ValidationException {

    public UpdateRentalDataValidationException(String details) {
        super(String.format("Update rental data validation failed. Details: %s", details));
    }
}
