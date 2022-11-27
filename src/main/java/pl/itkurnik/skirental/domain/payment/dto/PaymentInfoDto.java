package pl.itkurnik.skirental.domain.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class PaymentInfoDto {
    private Integer id;
    private Integer rentId;
    private BigDecimal amount;
    private PaymentMethodInfoDto paymentMethod;
    private Instant realisationDate;
    private String description;
}
