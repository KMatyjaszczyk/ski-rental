package pl.itkurnik.skirental.domain.payment.util;

import pl.itkurnik.skirental.domain.payment.Payment;
import pl.itkurnik.skirental.domain.payment.dto.PaymentInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentMapper {

    public static PaymentInfoDto mapToInfoDto(Payment payment) {
        PaymentInfoDto paymentInfo = new PaymentInfoDto();

        paymentInfo.setId(payment.getId());
        paymentInfo.setRentId(payment.getRent().getId());
        paymentInfo.setAmount(payment.getAmount());
        paymentInfo.setPaymentMethod(PaymentMethodMapper.mapToInfoDto(payment.getPaymentMethod()));
        paymentInfo.setRealisationDate(payment.getRealisationDate());
        paymentInfo.setDescription(payment.getDescription());

        return paymentInfo;
    }

    public static List<PaymentInfoDto> mapAllToInfoDto(List<Payment> payments) {
        return payments.stream()
                .map(PaymentMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
