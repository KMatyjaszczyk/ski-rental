package pl.itkurnik.skirental.domain.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemStatusInfoDto {
    private Integer id;
    private String name;
    private String description;
}
