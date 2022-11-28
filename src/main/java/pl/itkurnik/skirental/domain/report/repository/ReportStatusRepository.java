package pl.itkurnik.skirental.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.report.ReportStatus;

public interface ReportStatusRepository extends JpaRepository<ReportStatus, Integer> {
}