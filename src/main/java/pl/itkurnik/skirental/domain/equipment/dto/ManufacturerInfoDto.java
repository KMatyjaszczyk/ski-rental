package pl.itkurnik.skirental.domain.equipment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManufacturerInfoDto {
    private Integer id;
    private String name;
    private String description;
}
