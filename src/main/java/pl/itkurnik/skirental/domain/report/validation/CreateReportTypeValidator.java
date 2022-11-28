package pl.itkurnik.skirental.domain.report.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.report.dto.CreateReportTypeRequest;
import pl.itkurnik.skirental.domain.report.exception.CreateReportTypeValidationException;

@Component
public class CreateReportTypeValidator {

    public void validate(CreateReportTypeRequest request) {
        validateName(request.getName());
    }

    private void validateName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new CreateReportTypeValidationException("Name is empty");
        }
    }
}
