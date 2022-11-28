package pl.itkurnik.skirental.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.report.ReportStatus;

import java.util.Optional;

public interface ReportStatusRepository extends JpaRepository<ReportStatus, Integer> {

    Optional<ReportStatus> findByName(String name);
}