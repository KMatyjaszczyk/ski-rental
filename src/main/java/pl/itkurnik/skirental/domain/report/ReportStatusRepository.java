package pl.itkurnik.skirental.domain.report;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportStatusRepository extends JpaRepository<ReportStatus, Integer> {
}