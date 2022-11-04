package pl.itkurnik.skirental.domain.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UpdateItemRequest {
    private Integer id;
    private Integer sizeId;
    private Integer itemStatusId;
    private LocalDate lastServiceDate;
    private BigDecimal purchasePrice;
    private String description;
    private BigDecimal priceValue;
}
