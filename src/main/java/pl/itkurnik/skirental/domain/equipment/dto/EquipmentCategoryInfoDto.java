package pl.itkurnik.skirental.domain.equipment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentCategoryInfoDto {
    private Integer id;
    private String name;
    private String description;
    private Set<SizeInfoDto> sizes;
}
