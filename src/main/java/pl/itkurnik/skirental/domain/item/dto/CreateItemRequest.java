package pl.itkurnik.skirental.domain.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreateItemRequest {
    private Integer equipmentId;
    private Integer sizeId;
    private LocalDate lastServiceDate;
    private BigDecimal purchasePrice;
    private String description;
    private PriceForCreateItemRequest defaultPrice;
}
