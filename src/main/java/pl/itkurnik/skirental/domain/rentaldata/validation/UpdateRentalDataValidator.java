package pl.itkurnik.skirental.domain.rentaldata.validation;

import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rentaldata.dto.UpdateRentalDataRequest;
import pl.itkurnik.skirental.domain.rentaldata.exception.UpdateRentalDataValidationException;
import pl.itkurnik.skirental.util.validation.EmptyStringValidator;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.util.List;

@Component
public class UpdateRentalDataValidator extends MultipleFieldsValidator<UpdateRentalDataRequest> {

    @Override
    protected void processFieldsValidation(UpdateRentalDataRequest request, List<String> errorMessages) {
        EmptyStringValidator.validate(request.getCompanyName(), "Company name", errorMessages);
        EmptyStringValidator.validate(request.getTaxId(), "Tax id", errorMessages);
        EmptyStringValidator.validate(request.getRegon(), "REGON", errorMessages);
        EmptyStringValidator.validate(request.getEmailAddress(), "Email address", errorMessages);
        EmptyStringValidator.validate(request.getCity(), "City", errorMessages);
        EmptyStringValidator.validate(request.getPostCode(), "Postal code", errorMessages);
        EmptyStringValidator.validate(request.getStreet(), "Street", errorMessages);
        EmptyStringValidator.validate(request.getHouseNumber(), "House number", errorMessages);
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return UpdateRentalDataValidationException.class;
    }
}
