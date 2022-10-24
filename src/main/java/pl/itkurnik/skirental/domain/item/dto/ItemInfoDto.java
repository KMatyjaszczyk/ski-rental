package pl.itkurnik.skirental.domain.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.itkurnik.skirental.domain.equipment.dto.EquipmentFlatInfoDto;
import pl.itkurnik.skirental.domain.equipment.dto.SizeFlatInfoDto;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ItemInfoDto {
    private Integer id;
    private EquipmentFlatInfoDto equipment;
    private SizeFlatInfoDto size;
    private ItemStatusInfoDto itemStatus;
    private PriceInfoDto actualPrice;
    private LocalDate lastServiceDate;
    private BigDecimal purchasePrice;
    private String description;
}
