package pl.itkurnik.skirental.domain.equipment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentImageInfoDto {
    private Integer id;
    private String imageUuid;
    private Integer equipmentId;
    private String description;
}
