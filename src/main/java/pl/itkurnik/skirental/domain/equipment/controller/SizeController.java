package pl.itkurnik.skirental.domain.equipment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.equipment.Size;
import pl.itkurnik.skirental.domain.equipment.dto.CreateSizeRequest;
import pl.itkurnik.skirental.domain.equipment.dto.SizeInfoDto;
import pl.itkurnik.skirental.domain.equipment.dto.UpdateSizeRequest;
import pl.itkurnik.skirental.domain.equipment.exception.CreateSizeValidationException;
import pl.itkurnik.skirental.domain.equipment.exception.EquipmentCategoryNotFoundException;
import pl.itkurnik.skirental.domain.equipment.exception.SizeNotFoundException;
import pl.itkurnik.skirental.domain.equipment.exception.UpdateSizeValidationException;
import pl.itkurnik.skirental.domain.equipment.service.SizeService;
import pl.itkurnik.skirental.domain.equipment.util.SizeMapper;

import java.util.List;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/size")
@RequiredArgsConstructor
@Slf4j
public class SizeController {
    private final SizeService sizeService;

    @GetMapping("/{id}")
    public ResponseEntity<SizeInfoDto> findById(@PathVariable Integer id) {
        try {
            log.info("Receiving size with id {}", id);
            Size size = sizeService.findById(id);
            log.info("Size with id {} received successfully", id);
            return ResponseEntity.ok(SizeMapper.mapToInfoDto(size));
        } catch (SizeNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/equipment_category/{categoryId}")
    public ResponseEntity<List<SizeInfoDto>> findAllByEquipmentCategoryId(@PathVariable Integer categoryId) {
        try {
            log.info("Receiving all sizes with equipment category id {}", categoryId);
            List<Size> sizes = sizeService.findAllByEquipmentCategoryId(categoryId);
            log.info("All sizes with equipment category id {} received successfully", categoryId);
            return ResponseEntity.ok(SizeMapper.mapAllToInfoDto(sizes));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateSizeRequest request) {
        try {
            log.info("Creating size {}", request.getSize());
            sizeService.create(request);
            log.info("Size {} successfully created", request.getSize());
            return ResponseEntity.ok().build();
        } catch (CreateSizeValidationException | EquipmentCategoryNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateSizeRequest request) {
        try {
            log.info("Updating size with ID {}", request.getId());
            sizeService.update(request);
            log.info("Size with ID {} updated successfully", request.getId());
            return ResponseEntity.ok().build();
        } catch (UpdateSizeValidationException | EquipmentCategoryNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (SizeNotFoundException e) {
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
            log.info("Deleting size with ID {}", id);
            sizeService.deleteById(id);
            log.info("Size with ID {} successfully deleted", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
