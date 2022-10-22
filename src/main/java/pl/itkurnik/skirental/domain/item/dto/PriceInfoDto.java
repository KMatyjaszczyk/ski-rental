package pl.itkurnik.skirental.domain.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class PriceInfoDto {
    private Integer id;
    private Integer itemId;
    private BigDecimal priceValue;
    private Instant validFrom;
    private Instant validTo;
    private String description;
}
