package pl.itkurnik.skirental.domain.rent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateClientDocumentTypeRequest {
    private Integer id;
    private String name;
    private String description;
}
