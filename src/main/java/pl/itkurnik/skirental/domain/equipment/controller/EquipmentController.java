package pl.itkurnik.skirental.domain.equipment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.equipment.Equipment;
import pl.itkurnik.skirental.domain.equipment.dto.CreateEquipmentRequest;
import pl.itkurnik.skirental.domain.equipment.dto.EquipmentInfoDto;
import pl.itkurnik.skirental.domain.equipment.dto.UpdateEquipmentRequest;
import pl.itkurnik.skirental.domain.equipment.exception.*;
import pl.itkurnik.skirental.domain.equipment.service.EquipmentService;
import pl.itkurnik.skirental.domain.equipment.util.EquipmentMapper;

import java.util.List;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
@Slf4j
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentInfoDto> findById(@PathVariable Integer id) {
        try {
            log.info("Receiving equipment with id {}", id);
            Equipment equipment = equipmentService.findById(id);
            log.info("Equipment with id {} received successfully", id);
            return ResponseEntity.ok(EquipmentMapper.mapToInfoDto(equipment));
        } catch (EquipmentNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping
    public ResponseEntity<List<EquipmentInfoDto>> findAll() {
        try {
            log.info("Receiving all equipment");
            List<Equipment> equipments = equipmentService.findAll();
            log.info("All equipment received successfully");
            return ResponseEntity.ok(EquipmentMapper.mapAllToInfoDto(equipments));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/manufacturer/{manufacturerId}")
    public ResponseEntity<List<EquipmentInfoDto>> findAllByManufacturerId(@PathVariable Integer manufacturerId) {
        try {
            log.info("Receiving all equipment with manufacturer's id {}", manufacturerId);
            List<Equipment> equipments = equipmentService.findAllByManufacturerId(manufacturerId);
            log.info("All equipment with manufacturer's id {} received successfully", manufacturerId);
            return ResponseEntity.ok(EquipmentMapper.mapAllToInfoDto(equipments));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<EquipmentInfoDto>> findAllByCategoryId(@PathVariable Integer categoryId) {
        try {
            log.info("Receiving all equipment with category id {}", categoryId);
            List<Equipment> equipments = equipmentService.findAllByCategoryId(categoryId);
            log.info("All equipment with category id {} received successfully", categoryId);
            return ResponseEntity.ok(EquipmentMapper.mapAllToInfoDto(equipments));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PostMapping
    public ResponseEntity<EquipmentInfoDto> create(@RequestBody CreateEquipmentRequest request) {
        try {
            log.info("Creating equipment {}", request.getModel());
            Equipment equipment = equipmentService.create(request);
            log.info("Equipment {} successfully created", request.getModel());
            return ResponseEntity.ok(EquipmentMapper.mapToInfoDto(equipment));
        } catch (CreateEquipmentValidationException | ManufacturerNotFoundException |
                 EquipmentCategoryNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateEquipmentRequest request) {
        try {
            log.info("Updating equipment with ID {}", request.getId());
            equipmentService.update(request);
            log.info("Equipment with ID {} updated successfully", request.getId());
            return ResponseEntity.ok().build();
        } catch (UpdateEquipmentValidationException | ManufacturerNotFoundException |
                 EquipmentCategoryNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (EquipmentNotFoundException e) {
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
            log.info("Deleting equipment with ID {}", id);
            equipmentService.deleteById(id);
            log.info("Equipment with ID {} successfully deleted", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
