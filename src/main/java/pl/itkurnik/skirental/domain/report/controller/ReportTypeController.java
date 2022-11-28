package pl.itkurnik.skirental.domain.report.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.report.ReportType;
import pl.itkurnik.skirental.domain.report.dto.CreateReportTypeRequest;
import pl.itkurnik.skirental.domain.report.dto.ReportTypeInfoDto;
import pl.itkurnik.skirental.domain.report.exception.CreateReportTypeValidationException;
import pl.itkurnik.skirental.domain.report.exception.ReportTypeNotFoundException;
import pl.itkurnik.skirental.domain.report.service.ReportTypeService;
import pl.itkurnik.skirental.domain.report.util.ReportTypeMapper;

import java.util.List;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/report_type")
@RequiredArgsConstructor
@Slf4j
public class ReportTypeController {
    private final ReportTypeService reportTypeService;

    @GetMapping
    public ResponseEntity<List<ReportTypeInfoDto>> findAll() {
        try {
            log.info("Receiving all report types");
            List<ReportType> reportTypes = reportTypeService.findAll();
            log.info("All report types received successfully");
            return ResponseEntity.ok(ReportTypeMapper.mapAllToInfoDto(reportTypes));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportTypeInfoDto> findById(@PathVariable Integer id) {
        try {
            log.info("Receiving report type with id {}", id);
            ReportType reportType = reportTypeService.findById(id);
            log.info("Report type with id {} received successfully", id);
            return ResponseEntity.ok(ReportTypeMapper.mapToInfoDto(reportType));
        } catch (ReportTypeNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateReportTypeRequest request) {
        try {
            log.info("Creating report type: {}", request.getName());
            reportTypeService.create(request);
            log.info("Report type {} created successfully", request.getName());
            return ResponseEntity.ok().build();
        } catch (CreateReportTypeValidationException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        try {
            log.info("Deleting report type with id {}", id);
            reportTypeService.deleteById(id);
            log.info("Report type with id {} deleted successfully", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
