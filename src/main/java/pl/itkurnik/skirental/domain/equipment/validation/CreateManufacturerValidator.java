package pl.itkurnik.skirental.domain.equipment.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.equipment.dto.CreateManufacturerRequest;
import pl.itkurnik.skirental.domain.equipment.exception.CreateManufacturerValidationException;

@Component
public class CreateManufacturerValidator {

    public void validate(CreateManufacturerRequest request) {
        if (StringUtils.isBlank(request.getName())) {
            throw new CreateManufacturerValidationException("Name is empty");
        }
    }
}
