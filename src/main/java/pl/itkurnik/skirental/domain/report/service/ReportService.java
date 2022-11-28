package pl.itkurnik.skirental.domain.report.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.rent.Rent;
import pl.itkurnik.skirental.domain.rent.service.RentService;
import pl.itkurnik.skirental.domain.report.Report;
import pl.itkurnik.skirental.domain.report.ReportStatus;
import pl.itkurnik.skirental.domain.report.ReportStatuses;
import pl.itkurnik.skirental.domain.report.ReportType;
import pl.itkurnik.skirental.domain.report.dto.CreateReportRequest;
import pl.itkurnik.skirental.domain.report.exception.ReportNotFoundException;
import pl.itkurnik.skirental.domain.report.repository.ReportRepository;
import pl.itkurnik.skirental.domain.report.validation.CreateReportFieldsValidator;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final ReportTypeService reportTypeService;
    private final ReportStatusService reportStatusService;
    private final RentService rentService;
    private final CreateReportFieldsValidator createReportFieldsValidator;

    public Report findById(Integer id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
    }

    public List<Report> findAllByRentId(Integer rentId) {
        return reportRepository.findAllByRent_Id(rentId);
    }

    public void create(CreateReportRequest request) {
        createReportFieldsValidator.validateFields(request);

        createReport(request);
    }

    private void createReport(CreateReportRequest request) {
        Rent rent = rentService.findById(request.getRentId());
        ReportType reportType = reportTypeService.findById(request.getReportTypeId());
        ReportStatus openStatus = reportStatusService.getOpenStatus();

        Report report = new Report();
        report.setRent(rent);
        report.setReportDate(Instant.now());
        report.setReportType(reportType);
        report.setDescription(request.getDescription());
        report.setReportStatus(openStatus);

        reportRepository.save(report);
    }

    public void closeById(Integer id) {
        Report reportToClose = findById(id);
        validateIfReportIsAlreadyClosed(reportToClose);

        reportToClose.setReportStatus(reportStatusService.getClosedStatus());

        reportRepository.save(reportToClose);
    }

    private void validateIfReportIsAlreadyClosed(Report report) {
        boolean reportIsAlreadyClosed = report.getReportStatus().getName().equals(
                ReportStatuses.CLOSED.getName());

        if (reportIsAlreadyClosed) {
            throw new IllegalStateException(String.format("Report with id %d is already closed", report.getId()));
        }
    }
}
