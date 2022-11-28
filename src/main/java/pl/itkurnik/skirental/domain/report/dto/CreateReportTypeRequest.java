package pl.itkurnik.skirental.domain.report.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateReportTypeRequest {
    private String name;
    private String description;
}
