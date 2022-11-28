package pl.itkurnik.skirental.domain.rentaldata.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.rentaldata.dto.UpdateRentalDataRequest;
import pl.itkurnik.skirental.domain.rentaldata.exception.UpdateRentalDataValidationException;
import pl.itkurnik.skirental.domain.rentaldata.service.RentalDataService;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/rental_data")
@RequiredArgsConstructor
@Slf4j
public class RentalDataController {
    private final RentalDataService rentalDataService;

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateRentalDataRequest request) {
        try {
            log.info("Updating rental data");
            rentalDataService.update(request);
            log.info("Rental data updated successfully");
            return ResponseEntity.ok().build();
        } catch (UpdateRentalDataValidationException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
