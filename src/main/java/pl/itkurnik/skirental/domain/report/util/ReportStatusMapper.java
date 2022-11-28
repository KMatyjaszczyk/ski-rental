package pl.itkurnik.skirental.domain.report.util;

import pl.itkurnik.skirental.domain.report.ReportStatus;
import pl.itkurnik.skirental.domain.report.dto.ReportStatusInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class ReportStatusMapper {

    public static ReportStatusInfoDto mapToInfoDto(ReportStatus reportStatus) {
        ReportStatusInfoDto reportStatusInfo = new ReportStatusInfoDto();

        reportStatusInfo.setId(reportStatus.getId());
        reportStatusInfo.setName(reportStatus.getName());
        reportStatusInfo.setDescription(reportStatus.getDescription());

        return reportStatusInfo;
    }

    public static List<ReportStatusInfoDto> mapAllToInfoDto(List<ReportStatus> reportStatuses) {
        return reportStatuses.stream()
                .map(ReportStatusMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
