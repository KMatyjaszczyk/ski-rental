package pl.itkurnik.skirental.domain.equipment.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.equipment.dto.UpdateManufacturerRequest;
import pl.itkurnik.skirental.domain.equipment.exception.UpdateManufacturerValidationException;

import java.util.Objects;

@Component
public class UpdateManufacturerValidator {

    public void validate(UpdateManufacturerRequest request) {
        if (Objects.isNull(request.getId())) {
            throw new UpdateManufacturerValidationException("Manufacturer's id is null");
        }

        if (StringUtils.isBlank(request.getName())) {
            throw new UpdateManufacturerValidationException("Name is empty");
        }
    }
}
