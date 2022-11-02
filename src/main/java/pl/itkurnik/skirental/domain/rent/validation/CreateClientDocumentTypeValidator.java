package pl.itkurnik.skirental.domain.rent.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rent.dto.CreateClientDocumentTypeRequest;
import pl.itkurnik.skirental.domain.rent.exception.CreateClientDocumentTypeValidationException;

@Component
public class CreateClientDocumentTypeValidator {

    public void validate(CreateClientDocumentTypeRequest request) {
        if (StringUtils.isBlank(request.getName())) { // TODO KM change EmptyStringValidator, separate method for list of error messages and single field
            throw new CreateClientDocumentTypeValidationException("Name is empty");
        }
    }
}
