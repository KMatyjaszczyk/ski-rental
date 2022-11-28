package pl.itkurnik.skirental.domain.report.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateReportRequest {
    private Integer rentId;
    private Integer reportTypeId;
    private String description;
}
