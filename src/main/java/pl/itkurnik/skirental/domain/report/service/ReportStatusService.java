package pl.itkurnik.skirental.domain.report.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.report.ReportStatus;
import pl.itkurnik.skirental.domain.report.ReportStatuses;
import pl.itkurnik.skirental.domain.report.exception.ReportStatusNotFoundException;
import pl.itkurnik.skirental.domain.report.repository.ReportStatusRepository;

@Service
@RequiredArgsConstructor
public class ReportStatusService {
    private final ReportStatusRepository reportStatusRepository;

    public ReportStatus getOpenStatus() {
        return findByName(ReportStatuses.OPEN.getName());
    }

    public ReportStatus getClosedStatus() {
        return findByName(ReportStatuses.CLOSED.getName());
    }

    private ReportStatus findByName(String name) {
        return reportStatusRepository.findByName(name)
                .orElseThrow(() -> new ReportStatusNotFoundException(name));
    }
}
