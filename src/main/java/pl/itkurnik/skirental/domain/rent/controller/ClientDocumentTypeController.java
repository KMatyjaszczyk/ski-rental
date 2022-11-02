package pl.itkurnik.skirental.domain.rent.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.rent.ClientDocumentType;
import pl.itkurnik.skirental.domain.rent.dto.ClientDocumentTypeInfoDto;
import pl.itkurnik.skirental.domain.rent.dto.CreateClientDocumentTypeRequest;
import pl.itkurnik.skirental.domain.rent.dto.UpdateClientDocumentTypeRequest;
import pl.itkurnik.skirental.domain.rent.exception.ClientDocumentTypeNotFoundException;
import pl.itkurnik.skirental.domain.rent.exception.CreateClientDocumentTypeValidationException;
import pl.itkurnik.skirental.domain.rent.exception.UpdateClientDocumentTypeValidationException;
import pl.itkurnik.skirental.domain.rent.service.ClientDocumentTypeService;
import pl.itkurnik.skirental.domain.rent.util.ClientDocumentTypeMapper;

import java.util.List;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/client_document_type")
@RequiredArgsConstructor
@Slf4j
public class ClientDocumentTypeController {
    private final ClientDocumentTypeService clientDocumentTypeService;

    @GetMapping
    public ResponseEntity<List<ClientDocumentTypeInfoDto>> findAll() {
        try {
            log.info("Receiving all client document types");
            List<ClientDocumentType> clientDocumentTypes = clientDocumentTypeService.findAll();
            log.info("All client document types received successfully");
            return ResponseEntity.ok(ClientDocumentTypeMapper.mapAllToInfoDto(clientDocumentTypes));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDocumentTypeInfoDto> findById(@PathVariable Integer id) {
        try {
            log.info("Receiving client document type with id {}", id);
            ClientDocumentType clientDocumentType = clientDocumentTypeService.findById(id);
            log.info("Client document type with id {} received successfully", id);
            return ResponseEntity.ok(ClientDocumentTypeMapper.mapToInfoDto(clientDocumentType));
        } catch (ClientDocumentTypeNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateClientDocumentTypeRequest request) {
        try {
            log.info("Creating client document type {}", request.getName());
            clientDocumentTypeService.create(request);
            log.info("Client document type {} created successfully", request.getName());
            return ResponseEntity.ok().build();
        } catch (CreateClientDocumentTypeValidationException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateClientDocumentTypeRequest request) {
        try {
            log.info("Updating client document type with id {}", request.getId());
            clientDocumentTypeService.update(request);
            log.info("Client document type with id {} updated successfully", request.getId());
            return ResponseEntity.ok().build();
        } catch (UpdateClientDocumentTypeValidationException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (ClientDocumentTypeNotFoundException e) {
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
            log.info("Deleting client document type with ID {}", id);
            clientDocumentTypeService.deleteById(id);
            log.info("Client document type with ID {} deleted successfully", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
