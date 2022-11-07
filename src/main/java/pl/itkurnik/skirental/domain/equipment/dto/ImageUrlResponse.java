package pl.itkurnik.skirental.domain.equipment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageUrlResponse {
    private Integer id;
    private Integer equipmentId;
    private String url;
}
