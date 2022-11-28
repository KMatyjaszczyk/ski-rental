package pl.itkurnik.skirental.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.report.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    List<Report> findAllByRent_Id(Integer rentId);
}