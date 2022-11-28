package pl.itkurnik.skirental.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.report.ReportType;

public interface ReportTypeRepository extends JpaRepository<ReportType, Integer> {
}