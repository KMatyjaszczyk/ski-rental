package pl.itkurnik.skirental.domain.report.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.report.ReportType;
import pl.itkurnik.skirental.domain.report.dto.CreateReportTypeRequest;
import pl.itkurnik.skirental.domain.report.exception.ReportTypeNotFoundException;
import pl.itkurnik.skirental.domain.report.repository.ReportTypeRepository;
import pl.itkurnik.skirental.domain.report.validation.CreateReportTypeValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportTypeService {
    private final ReportTypeRepository reportTypeRepository;
    private final CreateReportTypeValidator createReportTypeValidator;

    public List<ReportType> findAll() {
        return reportTypeRepository.findAll();
    }

    public ReportType findById(Integer id) {
        return reportTypeRepository.findById(id)
                .orElseThrow(() -> new ReportTypeNotFoundException(id));
    }

    public void create(CreateReportTypeRequest request) {
        createReportTypeValidator.validate(request);

        createReportType(request);
    }

    private void createReportType(CreateReportTypeRequest request) {
        ReportType reportType = new ReportType();
        reportType.setName(request.getName());
        reportType.setDescription(request.getDescription());

        reportTypeRepository.save(reportType);
    }

    public void deleteById(Integer id) {
        boolean reportTypeDoesNotExist = !reportTypeRepository.existsById(id);

        if (reportTypeDoesNotExist) {
            return;
        }

        reportTypeRepository.deleteById(id);
    }
}
