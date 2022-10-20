package pl.itkurnik.skirental.domain.equipment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateSizeRequest {
    private Integer id;
    private String size;
    private Integer equipmentCategoryId;
    private String description;
}
