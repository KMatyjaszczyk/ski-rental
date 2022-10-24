package pl.itkurnik.skirental.domain.equipment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateEquipmentCategoryRequest {
    private String name;
    private String description;
    private List<SizeForCreateEquipmentCategoryRequest> sizes;
}
