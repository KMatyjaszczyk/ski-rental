package pl.itkurnik.skirental.domain.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class PriceForCreateItemRequest {
    private BigDecimal priceValue;
    private String description;
}
