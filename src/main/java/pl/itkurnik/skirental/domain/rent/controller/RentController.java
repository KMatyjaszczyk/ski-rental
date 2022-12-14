package pl.itkurnik.skirental.domain.rent.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.item.exception.ItemNotFoundException;
import pl.itkurnik.skirental.domain.rent.Rent;
import pl.itkurnik.skirental.domain.rent.dto.CreateRentRequest;
import pl.itkurnik.skirental.domain.rent.dto.RentInfoDto;
import pl.itkurnik.skirental.domain.rent.dto.ReturnRentItemRequest;
import pl.itkurnik.skirental.domain.rent.exception.CreateRentValidationException;
import pl.itkurnik.skirental.domain.rent.exception.RentNotFoundException;
import pl.itkurnik.skirental.domain.rent.exception.ReturnRentItemsValidationException;
import pl.itkurnik.skirental.domain.rent.service.RentService;
import pl.itkurnik.skirental.domain.rent.util.RentMapper;
import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

import java.math.BigDecimal;
import java.util.List;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/rent")
@RequiredArgsConstructor
@Slf4j
public class RentController {
    private final RentService rentService;

    @GetMapping
    public ResponseEntity<List<RentInfoDto>> findAll() {
        try {
            log.info("Receiving all rents");
            List<Rent> rents = rentService.findAll();
            log.info("All rents received successfully");
            return ResponseEntity.ok(RentMapper.mapAllToInfoDto(rents));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentInfoDto> findById(@PathVariable Integer id) {
        try {
            log.info("Receiving rent with id {}", id);
            Rent rent = rentService.findById(id);
            log.info("Rent with id {} received successfully", id);
            return ResponseEntity.ok(RentMapper.mapToInfoDto(rent));
        } catch (RentNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<RentInfoDto>> findAllByClientId(@PathVariable Integer clientId) {
        try {
            log.info("Receiving all rents for client with id {}", clientId);
            List<Rent> rents = rentService.findAllByClientId(clientId);
            log.info("All rents for client with id {} received successfully", clientId);
            return ResponseEntity.ok(RentMapper.mapAllToInfoDto(rents));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

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

    @PutMapping("/item/return")
    public ResponseEntity<Void> returnItem(@RequestBody ReturnRentItemRequest request) {
        try {
            log.info("Returning item for rent with id {}", request.getRentId());
            rentService.returnItem(request);
            log.info("Item for rent with id {} returned successfully", request.getRentId());
            return ResponseEntity.ok().build();
        } catch (RentNotFoundException | ItemNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (ReturnRentItemsValidationException | ObjectNotFoundException e) {
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

    @PutMapping("/finish/{id}")
    public ResponseEntity<Void> finishById(@PathVariable Integer id) {
        try {
            log.info("Finishing rent with id {}", id);
            rentService.finishRentById(id);
            log.info("Rent with id {} successfully finished", id);
            return ResponseEntity.ok().build();
        } catch (RentNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (ReturnRentItemsValidationException e) {
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

    @GetMapping("/{id}/cost")
    public ResponseEntity<BigDecimal> calculateCostById(@PathVariable Integer id) {
        try {
            log.info("Calculating cost for rent with id {}", id);
            BigDecimal costForRent = rentService.calculateCostForRentById(id);
            log.info("Cost for rent with id {} calculated successfully", id);
            return ResponseEntity.ok(costForRent);
        } catch (RentNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (IllegalStateException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/{id}/payments")
    public ResponseEntity<BigDecimal> calculatePaymentsAmountById(@PathVariable Integer id) {
        try {
            log.info("Calculating payments amount for rent with id {}", id);
            BigDecimal paymentsAmount = rentService.calculatePaymentsAmountById(id);
            log.info("Payments amount for rent with id {} calculated successfully", id);
            return ResponseEntity.ok(paymentsAmount);
        } catch (RentNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
