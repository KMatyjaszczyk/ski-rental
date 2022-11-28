package pl.itkurnik.skirental.domain.report.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class ReportInfoDto {
    private Integer id;
    private Integer rentId;
    private Instant reportDate;
    private ReportTypeInfoDto reportType;
    private String description;
    private ReportStatusInfoDto reportStatus;
}
