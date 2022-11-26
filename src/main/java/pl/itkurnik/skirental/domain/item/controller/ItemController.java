package pl.itkurnik.skirental.domain.item.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.equipment.exception.EquipmentNotFoundException;
import pl.itkurnik.skirental.domain.equipment.exception.SizeNotFoundException;
import pl.itkurnik.skirental.domain.item.Item;
import pl.itkurnik.skirental.domain.item.dto.CalculateItemsRentCostRequest;
import pl.itkurnik.skirental.domain.item.dto.CreateItemRequest;
import pl.itkurnik.skirental.domain.item.dto.ItemInfoDto;
import pl.itkurnik.skirental.domain.item.dto.UpdateItemRequest;
import pl.itkurnik.skirental.domain.item.exception.*;
import pl.itkurnik.skirental.domain.item.service.ItemService;
import pl.itkurnik.skirental.domain.item.util.ItemMapper;

import java.math.BigDecimal;
import java.util.List;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemInfoDto>> findAll() {
        try {
            log.info("Receiving all items");
            List<Item> items = itemService.findAll();
            log.info("All items received successfully");
            return ResponseEntity.ok(ItemMapper.mapAllToInfoDto(items));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/open")
    public ResponseEntity<List<ItemInfoDto>> findAllOpen() {
        try {
            log.info("Receiving all open items");
            List<Item> items = itemService.findAllOpen();
            log.info("All open items received successfully");
            return ResponseEntity.ok(ItemMapper.mapAllToInfoDto(items));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemInfoDto> findById(@PathVariable Integer id) {
        try {
            log.info("Receiving item with id {}", id);
            Item item = itemService.findById(id);
            log.info("Item with id {} received successfully", id);
            return ResponseEntity.ok(ItemMapper.mapToInfoDto(item));
        } catch (ItemNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<List<ItemInfoDto>> findAllByEquipmentId(@PathVariable Integer equipmentId) {
        try {
            log.info("Receiving all items with equipment's id {}", equipmentId);
            List<Item> items = itemService.findAllByEquipmentId(equipmentId);
            log.info("All items with equipment's id {} received successfully", equipmentId);
            return ResponseEntity.ok(ItemMapper.mapAllToInfoDto(items));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/size/{sizeId}")
    public ResponseEntity<List<ItemInfoDto>> findAllBySizeId(@PathVariable Integer sizeId) {
        try {
            log.info("Receiving all items with size id {}", sizeId);
            List<Item> items = itemService.findAllBySizeId(sizeId);
            log.info("All items with size id {} received successfully", sizeId);
            return ResponseEntity.ok(ItemMapper.mapAllToInfoDto(items));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateItemRequest request) {
        try {
            log.info("Creating item for equipment's id {} with size id {}", request.getEquipmentId(), request.getSizeId());
            itemService.create(request);
            log.info("Item with equipment's id {} and size id {} created successfully", request.getEquipmentId(), request.getSizeId());
            return ResponseEntity.ok().build();
        } catch (CreateItemValidationException | EquipmentNotFoundException | SizeNotFoundException |
                 ItemStatusNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateItemRequest request) {
        try {
            log.info("Updating item with ID {}", request.getId());
            itemService.update(request);
            log.info("Item with ID {} updated successfully", request.getId());
            return ResponseEntity.ok().build();
        } catch (UpdateItemValidationException | SizeNotFoundException | ItemStatusNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (ItemNotFoundException e) {
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
            log.info("Deleting item with ID {}", id);
            itemService.deleteById(id);
            log.info("Item with ID {} successfully deleted", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/items/cost")
    public ResponseEntity<BigDecimal> calculateItemsRentCost(@RequestBody CalculateItemsRentCostRequest request) {
        try {
            log.info("Calculating items cost for rent from {} to {}", request.getDateFrom(), request.getDateTo());
            BigDecimal itemsRentCost = itemService.calculateItemsRentCost(request);
            log.info("Items cost for rent from {} to {} calculated successfully", request.getDateFrom(), request.getDateTo());
            return ResponseEntity.ok(itemsRentCost);
        } catch (CalculateItemsRentCostValidationException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
