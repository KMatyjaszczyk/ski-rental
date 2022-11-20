package pl.itkurnik.skirental.domain.rent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.itkurnik.skirental.domain.item.dto.ItemInfoDto;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class RentItemInfoDto {
    private Integer id;
    private Integer rentId;
    private ItemInfoDto item;
    private BigDecimal rentPrice;
    private RentItemStatusInfoDto rentItemStatus;
    private Instant rentedFrom;
    private Instant rentedTo;
    private BigDecimal paidAmount;
    private String description;
}
