package pl.itkurnik.skirental.domain.report.util;

import pl.itkurnik.skirental.domain.report.Report;
import pl.itkurnik.skirental.domain.report.dto.ReportInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class ReportMapper {

    public static ReportInfoDto mapToInfoDto(Report report) {
        ReportInfoDto reportInfo = new ReportInfoDto();

        reportInfo.setId(report.getId());
        reportInfo.setRentId(report.getRent().getId());
        reportInfo.setReportDate(report.getReportDate());
        reportInfo.setReportType(ReportTypeMapper.mapToInfoDto(report.getReportType()));
        reportInfo.setDescription(report.getDescription());
        reportInfo.setReportStatus(ReportStatusMapper.mapToInfoDto(report.getReportStatus()));

        return reportInfo;
    }

    public static List<ReportInfoDto> mapAllToInfoDto(List<Report> reports) {
        return reports.stream()
                .map(ReportMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
