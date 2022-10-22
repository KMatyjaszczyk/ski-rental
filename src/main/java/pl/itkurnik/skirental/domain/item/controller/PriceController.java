package pl.itkurnik.skirental.domain.item.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.item.Price;
import pl.itkurnik.skirental.domain.item.dto.CreatePriceRequest;
import pl.itkurnik.skirental.domain.item.dto.PriceInfoDto;
import pl.itkurnik.skirental.domain.item.exception.CreatePriceValidationException;
import pl.itkurnik.skirental.domain.item.exception.ItemNotFoundException;
import pl.itkurnik.skirental.domain.item.exception.PriceNotFoundException;
import pl.itkurnik.skirental.domain.item.service.PriceService;
import pl.itkurnik.skirental.domain.item.util.PriceMapper;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/price")
@RequiredArgsConstructor
@Slf4j
public class PriceController {
    private final PriceService priceService;

    @GetMapping("/{id}")
    public ResponseEntity<PriceInfoDto> findById(@PathVariable Integer id) {
        try {
            log.info("Receiving price with id {}", id);
            Price price = priceService.findById(id);
            log.info("Price with id {} received successfully", id);
            return ResponseEntity.ok(PriceMapper.mapToInfoDto(price));
        } catch (PriceNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/item_actual/{itemId}")
    public ResponseEntity<PriceInfoDto> findActualPriceForItemByItemId(@PathVariable Integer itemId) {
        try {
            log.info("Receiving actual price for item with id {}", itemId);
            Price price = priceService.findActualPriceForItemByItemId(itemId);
            log.info("Actual price for item with id {} received successfully", itemId);
            return ResponseEntity.ok(PriceMapper.mapToInfoDto(price));
        } catch (ItemNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreatePriceRequest request) {
        try {
            log.info("Creating price with value {} for item with id {}", request.getPriceValue(), request.getItemId());
            priceService.create(request);
            log.info("Price with value {} for item with id {} created successfully", request.getPriceValue(), request.getItemId());
            return ResponseEntity.ok().build();
        } catch (CreatePriceValidationException | ItemNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
