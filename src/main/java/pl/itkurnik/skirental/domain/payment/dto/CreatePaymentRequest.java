package pl.itkurnik.skirental.domain.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CreatePaymentRequest {
    private Integer rentId;
    private BigDecimal amount;
    private Integer paymentMethodId;
}
