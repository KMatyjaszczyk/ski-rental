package pl.itkurnik.skirental.domain.rent.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.rent.RentStatus;
import pl.itkurnik.skirental.domain.rent.RentStatuses;
import pl.itkurnik.skirental.domain.rent.dto.RentStatusInfoDto;
import pl.itkurnik.skirental.domain.rent.exception.RentStatusNotFoundException;
import pl.itkurnik.skirental.domain.rent.service.RentStatusService;
import pl.itkurnik.skirental.domain.rent.util.RentStatusMapper;

import java.util.List;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/rent_status")
@RequiredArgsConstructor
@Slf4j
public class RentStatusController {
    private final RentStatusService rentStatusService;

    @GetMapping
    public ResponseEntity<List<RentStatusInfoDto>> findAll() {
        try {
            log.info("Receiving all rent statuses");
            List<RentStatus> rentStatuses = rentStatusService.findAll();
            log.info("All rent statuses received successfully");
            return ResponseEntity.ok(RentStatusMapper.mapAllToInfoDto(rentStatuses));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/rented")
    public ResponseEntity<RentStatusInfoDto> getRentedStatus() {
        try {
            log.info("Receiving {} rent status", RentStatuses.RENTED.getName());
            RentStatus rentedStatus = rentStatusService.getRentedStatus();
            log.info("{} rent status received successfully", RentStatuses.RENTED.getName());
            return ResponseEntity.ok(RentStatusMapper.mapToInfoDto(rentedStatus));
        } catch (RentStatusNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/finished")
    public ResponseEntity<RentStatusInfoDto> getFinishedStatus() {
        try {
            log.info("Receiving {} rent status", RentStatuses.FINISHED.getName());
            RentStatus finishedStatus = rentStatusService.getFinishedStatus();
            log.info("{} rent status received successfully", RentStatuses.FINISHED.getName());
            return ResponseEntity.ok(RentStatusMapper.mapToInfoDto(finishedStatus));
        } catch (RentStatusNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/paid")
    public ResponseEntity<RentStatusInfoDto> getPaidStatus() {
        try {
            log.info("Receiving {} rent status", RentStatuses.PAID.getName());
            RentStatus paidStatus = rentStatusService.getPaidStatus();
            log.info("{} rent status received successfully", RentStatuses.PAID.getName());
            return ResponseEntity.ok(RentStatusMapper.mapToInfoDto(paidStatus));
        } catch (RentStatusNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
