package pl.itkurnik.skirental.domain.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CreateItemRequest {
    private Integer equipmentId;
    private Integer sizeId;
    private BigDecimal purchasePrice;
    private String description;
}
