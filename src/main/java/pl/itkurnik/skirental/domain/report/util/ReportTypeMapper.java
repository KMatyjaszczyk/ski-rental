package pl.itkurnik.skirental.domain.report.util;

import pl.itkurnik.skirental.domain.report.ReportType;
import pl.itkurnik.skirental.domain.report.dto.ReportTypeInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class ReportTypeMapper {

    public static ReportTypeInfoDto mapToInfoDto(ReportType reportType) {
        ReportTypeInfoDto reportTypeInfo = new ReportTypeInfoDto();

        reportTypeInfo.setId(reportType.getId());
        reportTypeInfo.setName(reportType.getName());
        reportTypeInfo.setDescription(reportType.getDescription());

        return reportTypeInfo;
    }

    public static List<ReportTypeInfoDto> mapAllToInfoDto(List<ReportType> reportTypes) {
        return reportTypes.stream()
                .map(ReportTypeMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
