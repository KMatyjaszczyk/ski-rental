package pl.itkurnik.skirental.domain.report;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.report.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}