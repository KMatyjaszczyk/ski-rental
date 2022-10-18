package pl.itkurnik.skirental.domain.equipment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateEquipmentRequest {
    private Integer id;
    private String model;
    private Integer manufacturerId;
    private Integer equipmentCategoryId;
    private String description;
}
