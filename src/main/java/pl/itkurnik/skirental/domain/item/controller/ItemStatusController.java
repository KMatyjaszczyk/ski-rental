package pl.itkurnik.skirental.domain.item.controller;

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
import pl.itkurnik.skirental.domain.item.ItemStatus;
import pl.itkurnik.skirental.domain.item.ItemStatuses;
import pl.itkurnik.skirental.domain.item.dto.ItemStatusInfoDto;
import pl.itkurnik.skirental.domain.item.exception.ItemStatusNotFoundException;
import pl.itkurnik.skirental.domain.item.service.ItemStatusService;
import pl.itkurnik.skirental.domain.item.util.ItemStatusMapper;

import java.util.List;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/item_status")
@RequiredArgsConstructor
@Slf4j
public class ItemStatusController {
    private final ItemStatusService itemStatusService;

    @GetMapping
    public ResponseEntity<List<ItemStatusInfoDto>> findAll() {
        try {
            log.info("Receiving all item statuses");
            List<ItemStatus> itemStatuses = itemStatusService.findAll();
            log.info("All item statuses received successfully");
            return ResponseEntity.ok(ItemStatusMapper.mapAllToInfoDto(itemStatuses));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/open")
    public ResponseEntity<ItemStatusInfoDto> getOpenStatus() {
        try {
            log.info("Receiving {} item status", ItemStatuses.OPEN.getName());
            ItemStatus openStatus = itemStatusService.getOpenStatus();
            log.info("{} item status received successfully", ItemStatuses.OPEN.getName());
            return ResponseEntity.ok(ItemStatusMapper.mapToInfoDto(openStatus));
        } catch (ItemStatusNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/rented")
    public ResponseEntity<ItemStatusInfoDto> getRentedStatus() {
        try {
            log.info("Receiving {} item status", ItemStatuses.RENTED.getName());
            ItemStatus rentedStatus = itemStatusService.getRentedStatus();
            log.info("{} item status received successfully", ItemStatuses.RENTED.getName());
            return ResponseEntity.ok(ItemStatusMapper.mapToInfoDto(rentedStatus));
        } catch (ItemStatusNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/broken")
    public ResponseEntity<ItemStatusInfoDto> getBrokenStatus() {
        try {
            log.info("Receiving {} item status", ItemStatuses.BROKEN.getName());
            ItemStatus brokenStatus = itemStatusService.getBrokenStatus();
            log.info("{} item status received successfully", ItemStatuses.BROKEN.getName());
            return ResponseEntity.ok(ItemStatusMapper.mapToInfoDto(brokenStatus));
        } catch (ItemStatusNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/in_service")
    public ResponseEntity<ItemStatusInfoDto> getInServiceStatus() {
        try {
            log.info("Receiving {} item status", ItemStatuses.IN_SERVICE.getName());
            ItemStatus inServiceStatus = itemStatusService.getInServiceStatus();
            log.info("{} item status received successfully", ItemStatuses.IN_SERVICE.getName());
            return ResponseEntity.ok(ItemStatusMapper.mapToInfoDto(inServiceStatus));
        } catch (ItemStatusNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
