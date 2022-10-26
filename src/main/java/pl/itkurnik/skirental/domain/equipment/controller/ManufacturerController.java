package pl.itkurnik.skirental.domain.equipment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.equipment.Manufacturer;
import pl.itkurnik.skirental.domain.equipment.dto.CreateManufacturerRequest;
import pl.itkurnik.skirental.domain.equipment.dto.ManufacturerInfoDto;
import pl.itkurnik.skirental.domain.equipment.dto.UpdateManufacturerRequest;
import pl.itkurnik.skirental.domain.equipment.exception.CreateManufacturerValidationException;
import pl.itkurnik.skirental.domain.equipment.exception.ManufacturerNotFoundException;
import pl.itkurnik.skirental.domain.equipment.exception.UpdateManufacturerValidationException;
import pl.itkurnik.skirental.domain.equipment.service.ManufacturerService;
import pl.itkurnik.skirental.domain.equipment.util.ManufacturerMapper;

import java.util.List;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/manufacturer")
@RequiredArgsConstructor
@Slf4j
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<ManufacturerInfoDto>> findAll() {
        try {
            log.info("Receiving all manufacturers");
            List<Manufacturer> manufacturers = manufacturerService.findAll();
            log.info("All manufacturers successfully received");
            return ResponseEntity.ok(ManufacturerMapper.mapAllToInfoDto(manufacturers));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerInfoDto> findById(@PathVariable Integer id) {
        try {
            log.info("Receiving manufacturer with id {}", id);
            Manufacturer manufacturer = manufacturerService.findById(id);
            log.info("Manufacturer with id {} received successfully", id);
            return ResponseEntity.ok(ManufacturerMapper.mapToInfoDto(manufacturer));
        } catch (ManufacturerNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateManufacturerRequest request) {
        try {
            log.info("Creating manufacturer {}", request.getName());
            manufacturerService.create(request);
            log.info("Manufacturer {} successfully created", request.getName());
            return ResponseEntity.ok().build();
        } catch (CreateManufacturerValidationException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateManufacturerRequest request) {
        try {
            log.info("Updating manufacturer with ID {}", request.getId());
            manufacturerService.update(request);
            log.info("Manufacturer with ID {} updated successfully", request.getId());
            return ResponseEntity.ok().build();
        } catch (UpdateManufacturerValidationException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (ManufacturerNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        try {
            log.info("Deleting manufacturer with ID {}", id);
            manufacturerService.deleteById(id);
            log.info("Manufacturer with ID {} successfully deleted", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
