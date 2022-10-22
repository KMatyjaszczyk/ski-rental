package pl.itkurnik.skirental.domain.equipment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentFlatInfoDto {
    private Integer id;
    private String model;
    private ManufacturerInfoDto manufacturer;
    private EquipmentCategoryFlatInfoDto equipmentCategory;
    private String description;
}
