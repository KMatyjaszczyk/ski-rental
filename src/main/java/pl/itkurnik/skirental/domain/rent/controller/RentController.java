package pl.itkurnik.skirental.domain.rent.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.domain.rent.dto.CreateRentRequest;
import pl.itkurnik.skirental.domain.rent.service.RentService;

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
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e); // TODO KM insert default message here
        }
    }
}
