package pl.itkurnik.skirental.domain.equipment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SizeInfoDto {
    private Integer id;
    private String size;
    private EquipmentCategoryFlatInfoDto equipmentCategory;
    private String description;
}
