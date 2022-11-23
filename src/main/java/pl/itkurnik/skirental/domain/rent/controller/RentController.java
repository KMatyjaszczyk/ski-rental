package pl.itkurnik.skirental.domain.rent.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.rent.dto.CreateRentRequest;
import pl.itkurnik.skirental.domain.rent.dto.ReturnRentItemsRequest;
import pl.itkurnik.skirental.domain.rent.exception.CreateRentValidationException;
import pl.itkurnik.skirental.domain.rent.service.RentService;
import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/rent")
@RequiredArgsConstructor
@Slf4j
public class RentController {
    private final RentService rentService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateRentRequest request) {
        try {
            log.info("Creating rent for client with document {}", request.getClientDocumentNumber());
            rentService.create(request);
            log.info("Rent for client with document {} created successfully", request.getClientDocumentNumber());
            return ResponseEntity.ok().build();
        } catch (CreateRentValidationException | ObjectNotFoundException e) {
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

    @PutMapping("/items/return")
    public ResponseEntity<Void> returnItems(@RequestBody ReturnRentItemsRequest request) {
        try {
            log.info("Returning items for rent with id {}", request.getRentId());
            rentService.returnItems(request);
            log.info("Items for rent with id {} returned successfully", request.getRentId());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
