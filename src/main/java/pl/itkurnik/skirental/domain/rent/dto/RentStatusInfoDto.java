package pl.itkurnik.skirental.domain.rent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RentStatusInfoDto {
    private Integer id;
    private String name;
    private String description;
}
