package pl.itkurnik.skirental.domain.equipment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateEquipmentRequest {
    private String model;
    private Integer manufacturerId;
    private Integer equipmentCategoryId;
    private String description;
}
