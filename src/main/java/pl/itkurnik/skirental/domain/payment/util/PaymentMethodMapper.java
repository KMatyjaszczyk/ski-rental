package pl.itkurnik.skirental.domain.payment.util;

import pl.itkurnik.skirental.domain.payment.PaymentMethod;
import pl.itkurnik.skirental.domain.payment.dto.PaymentMethodInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentMethodMapper {

    public static PaymentMethodInfoDto mapToInfoDto(PaymentMethod paymentMethod) {
        PaymentMethodInfoDto paymentMethodInfo = new PaymentMethodInfoDto();

        paymentMethodInfo.setId(paymentMethod.getId());
        paymentMethodInfo.setName(paymentMethod.getName());
        paymentMethodInfo.setDescription(paymentMethod.getDescription());

        return paymentMethodInfo;
    }

    public static List<PaymentMethodInfoDto> mapAllToInfoDto(List<PaymentMethod> paymentMethods) {
        return paymentMethods.stream()
                .map(PaymentMethodMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
