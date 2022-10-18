package pl.itkurnik.skirental.domain.equipment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentInfoDto {
    private Integer id;
    private String model;
    private ManufacturerInfoDto manufacturer;
    private EquipmentCategoryInfoDto equipmentCategory;
    private String description;
}
