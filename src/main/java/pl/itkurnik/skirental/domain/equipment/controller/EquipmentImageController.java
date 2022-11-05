package pl.itkurnik.skirental.domain.equipment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.equipment.EquipmentImage;
import pl.itkurnik.skirental.domain.equipment.dto.CreateEquipmentImageRequest;
import pl.itkurnik.skirental.domain.equipment.dto.EquipmentImageInfoDto;
import pl.itkurnik.skirental.domain.equipment.exception.EquipmentImageNotFoundException;
import pl.itkurnik.skirental.domain.equipment.service.EquipmentImageService;
import pl.itkurnik.skirental.domain.equipment.util.EquipmentImageMapper;

import java.util.List;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/equipment_image")
@RequiredArgsConstructor
@Slf4j
public class EquipmentImageController {
    private final EquipmentImageService equipmentImageService;

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentImageInfoDto> findById(@PathVariable Integer id) {
        try {
            log.info("Receiving equipment image with id {}", id);
            EquipmentImage equipmentImage = equipmentImageService.findById(id);
            log.info("Equipment image with id {} received successfully", id);
            return ResponseEntity.ok(EquipmentImageMapper.mapToInfoDto(equipmentImage));
        } catch (EquipmentImageNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<List<EquipmentImageInfoDto>> findAllByIdEquipmentId(@PathVariable Integer equipmentId) {
        try {
            log.info("Receiving all images with equipment id {}", equipmentId);
            List<EquipmentImage> equipmentImages = equipmentImageService.findAllByEquipmentId(equipmentId);
            log.info("All images with equipment id {} received successfully", equipmentId);
            return ResponseEntity.ok(EquipmentImageMapper.mapAllToInfoDto(equipmentImages));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PostMapping("/equipment/{equipmentId}")
    public ResponseEntity<Void> create(@PathVariable Integer equipmentId, @RequestParam("image") MultipartFile image) {
        try {
            log.info("Creating image for equipment with id {}", equipmentId);
            CreateEquipmentImageRequest request = new CreateEquipmentImageRequest(equipmentId, image);
            equipmentImageService.create(request);
            log.info("Image for equipment with id {} created successfully", equipmentId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
