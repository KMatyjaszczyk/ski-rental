package pl.itkurnik.skirental.domain.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentMethodInfoDto {
    private Integer id;
    private String name;
    private String description;
}
