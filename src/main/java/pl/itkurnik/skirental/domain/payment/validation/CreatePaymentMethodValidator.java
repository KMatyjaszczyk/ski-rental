package pl.itkurnik.skirental.domain.payment.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.payment.dto.CreatePaymentMethodRequest;
import pl.itkurnik.skirental.domain.payment.exception.CreatePaymentMethodValidationException;

@Component
public class CreatePaymentMethodValidator {

    public void validate(CreatePaymentMethodRequest request) {
        if (StringUtils.isBlank(request.getName())) {
            throw new CreatePaymentMethodValidationException("Name is empty");
        }
    }
}
