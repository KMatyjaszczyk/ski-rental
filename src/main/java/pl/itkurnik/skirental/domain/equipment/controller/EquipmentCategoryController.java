package pl.itkurnik.skirental.domain.equipment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.equipment.EquipmentCategory;
import pl.itkurnik.skirental.domain.equipment.dto.CreateEquipmentCategoryRequest;
import pl.itkurnik.skirental.domain.equipment.dto.EquipmentCategoryInfoDto;
import pl.itkurnik.skirental.domain.equipment.dto.UpdateEquipmentCategoryRequest;
import pl.itkurnik.skirental.domain.equipment.exception.CreateEquipmentCategoryValidationException;
import pl.itkurnik.skirental.domain.equipment.exception.EquipmentCategoryNotFoundException;
import pl.itkurnik.skirental.domain.equipment.exception.UpdateEquipmentCategoryValidationException;
import pl.itkurnik.skirental.domain.equipment.service.EquipmentCategoryService;
import pl.itkurnik.skirental.domain.equipment.util.EquipmentCategoryMapper;

import java.util.List;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/equipment_category")
@RequiredArgsConstructor
@Slf4j
public class EquipmentCategoryController {
    private final EquipmentCategoryService equipmentCategoryService;

    @GetMapping
    public ResponseEntity<List<EquipmentCategoryInfoDto>> findAll() {
        try {
            log.info("Receiving all equipment categories");
            List<EquipmentCategory> equipmentCategories = equipmentCategoryService.findAll();
            log.info("All equipment categories successfully received");
            return ResponseEntity.ok(EquipmentCategoryMapper.mapAllToInfoDto(equipmentCategories));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentCategoryInfoDto> findById(@PathVariable Integer id) {
        try {
            log.info("Receiving equipment category with id {}", id);
            EquipmentCategory equipmentCategory = equipmentCategoryService.findById(id);
            log.info("Equipment category with id {} received successfully", id);
            return ResponseEntity.ok(EquipmentCategoryMapper.mapToInfoDto(equipmentCategory));
        } catch (EquipmentCategoryNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateEquipmentCategoryRequest request) {
        try {
            log.info("Creating equipment category {}", request.getName());
            equipmentCategoryService.create(request);
            log.info("Equipment category {} successfully created", request.getName());
            return ResponseEntity.ok().build();
        } catch (CreateEquipmentCategoryValidationException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateEquipmentCategoryRequest request) {
        try {
            log.info("Updating equipment category with ID {}", request.getId());
            equipmentCategoryService.update(request);
            log.info("Equipment category with ID {} updated successfully", request.getId());
            return ResponseEntity.ok().build();
        } catch (UpdateEquipmentCategoryValidationException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (EquipmentCategoryNotFoundException e) {
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
            log.info("Deleting equipment category with ID {}", id);
            equipmentCategoryService.deleteById(id);
            log.info("Equipment category with ID {} successfully deleted", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
