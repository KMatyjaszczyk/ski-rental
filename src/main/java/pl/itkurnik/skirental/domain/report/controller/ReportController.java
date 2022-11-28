package pl.itkurnik.skirental.domain.report.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.report.Report;
import pl.itkurnik.skirental.domain.report.dto.CreateReportRequest;
import pl.itkurnik.skirental.domain.report.dto.ReportInfoDto;
import pl.itkurnik.skirental.domain.report.exception.CreateReportValidationException;
import pl.itkurnik.skirental.domain.report.exception.ReportNotFoundException;
import pl.itkurnik.skirental.domain.report.service.ReportService;
import pl.itkurnik.skirental.domain.report.util.ReportMapper;
import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

import java.util.List;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
@Slf4j
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/{id}")
    public ResponseEntity<ReportInfoDto> findById(@PathVariable Integer id) {
        try {
            log.info("Receiving report with id {}", id);
            Report report = reportService.findById(id);
            log.info("Report with id {} received successfully", id);
            return ResponseEntity.ok(ReportMapper.mapToInfoDto(report));
        } catch (ReportNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/rent/{rentId}")
    public ResponseEntity<List<ReportInfoDto>> findAllByRentId(@PathVariable Integer rentId) {
        try {
            log.info("Receiving all reports for rent with id {}", rentId);
            List<Report> reports = reportService.findAllByRentId(rentId);
            log.info("All reports for rent with id {} received successfully", rentId);
            return ResponseEntity.ok(ReportMapper.mapAllToInfoDto(reports));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateReportRequest request) {
        try {
            log.info("Creating report for rent with id {}", request.getRentId());
            reportService.create(request);
            log.info("Report for rent with id {} created successfully", request.getRentId());
            return ResponseEntity.ok().build();
        } catch (CreateReportValidationException | ObjectNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<Void> closeById(@PathVariable Integer id) {
        try {
            log.info("Closing report with id {}", id);
            reportService.closeById(id);
            log.info("Report with id {} closed successfully", id);
            return ResponseEntity.ok().build();
        } catch (ReportNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (IllegalStateException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
