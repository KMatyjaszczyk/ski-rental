package pl.itkurnik.skirental.domain.equipment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.equipment.Manufacturer;
import pl.itkurnik.skirental.domain.equipment.dto.CreateManufacturerRequest;
import pl.itkurnik.skirental.domain.equipment.dto.UpdateManufacturerRequest;
import pl.itkurnik.skirental.domain.equipment.exception.ManufacturerNotFoundException;
import pl.itkurnik.skirental.domain.equipment.repository.ManufacturerRepository;
import pl.itkurnik.skirental.domain.equipment.validation.CreateManufacturerValidator;
import pl.itkurnik.skirental.domain.equipment.validation.UpdateManufacturerValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;
    private final CreateManufacturerValidator createManufacturerValidator;
    private final UpdateManufacturerValidator updateManufacturerValidator;

    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer findById(Integer id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new ManufacturerNotFoundException(id));
    }

    public void deleteById(Integer id) {
        try {
            manufacturerRepository.deleteById(id); // TODO KM preferred will be setting flag 'deleted'
        } catch (EmptyResultDataAccessException ignored) {
            log.info("Manufacturer with ID {} already deleted", id);
        }
    }

    public void create(CreateManufacturerRequest request) {
        createManufacturerValidator.validate(request);
        createManufacturer(request);
    }

    private void createManufacturer(CreateManufacturerRequest request) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(request.getName());
        manufacturer.setDescription(request.getDescription());
        manufacturerRepository.save(manufacturer);
    }

    public void update(UpdateManufacturerRequest request) {
        updateManufacturerValidator.validate(request);

        Manufacturer manufacturer = manufacturerRepository.findById(request.getId())
                .orElseThrow(() -> new ManufacturerNotFoundException(request.getId()));
        updateProperFields(request, manufacturer);

        manufacturerRepository.save(manufacturer);
    }

    private void updateProperFields(UpdateManufacturerRequest request, Manufacturer manufacturer) {
        if (!request.getName().equals(manufacturer.getName())) {
            manufacturer.setName(request.getName());
        }

        if (!request.getDescription().equals(manufacturer.getDescription())) {
            manufacturer.setDescription(request.getDescription());
        }
    }
}
