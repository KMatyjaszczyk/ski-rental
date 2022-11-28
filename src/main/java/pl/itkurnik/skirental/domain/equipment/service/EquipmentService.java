package pl.itkurnik.skirental.domain.equipment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.equipment.Equipment;
import pl.itkurnik.skirental.domain.equipment.EquipmentCategory;
import pl.itkurnik.skirental.domain.equipment.Manufacturer;
import pl.itkurnik.skirental.domain.equipment.dto.CreateEquipmentRequest;
import pl.itkurnik.skirental.domain.equipment.dto.UpdateEquipmentRequest;
import pl.itkurnik.skirental.domain.equipment.exception.EquipmentNotFoundException;
import pl.itkurnik.skirental.domain.equipment.repository.EquipmentRepository;
import pl.itkurnik.skirental.domain.equipment.validation.CreateEquipmentValidator;
import pl.itkurnik.skirental.domain.equipment.validation.UpdateEquipmentValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final ManufacturerService manufacturerService;
    private final EquipmentCategoryService equipmentCategoryService;
    private final CreateEquipmentValidator createEquipmentValidator;
    private final UpdateEquipmentValidator updateEquipmentValidator;

    public Equipment findById(Integer id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException(id));
    }

    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    public List<Equipment> findAllByManufacturerId(Integer manufacturerId) {
        return equipmentRepository.findAllByManufacturer_Id(manufacturerId);
    }

    public List<Equipment> findAllByCategoryId(Integer categoryId) {
        return equipmentRepository.findAllByEquipmentCategory_Id(categoryId);
    }

    public void deleteById(Integer id) {
        try {
            equipmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignored) {
            log.info("Equipment with ID {} already deleted", id);
        }
    }

    public Equipment create(CreateEquipmentRequest request) {
        createEquipmentValidator.validateFields(request);
        return createEquipment(request);
    }

    private Equipment createEquipment(CreateEquipmentRequest request) {
        Manufacturer manufacturer = manufacturerService.findById(
                request.getManufacturerId());
        EquipmentCategory category = equipmentCategoryService.findById(
                request.getEquipmentCategoryId());

        Equipment equipment = new Equipment();
        equipment.setModel(request.getModel());
        equipment.setManufacturer(manufacturer);
        equipment.setEquipmentCategory(category);
        equipment.setDescription(request.getDescription());

        return equipmentRepository.save(equipment);
    }

    public void update(UpdateEquipmentRequest request) {
        updateEquipmentValidator.validateFields(request);

        Equipment equipment = equipmentRepository.findById(request.getId())
                .orElseThrow(() -> new EquipmentNotFoundException(request.getId()));
        updateProperFields(request, equipment);

        equipmentRepository.save(equipment);
    }

    private void updateProperFields(UpdateEquipmentRequest request, Equipment equipment) {
        if (!StringUtils.equals(request.getModel(), equipment.getModel())) {
            equipment.setModel(request.getModel());
        }

        if (!request.getManufacturerId().equals(equipment.getManufacturer().getId())) {
            Manufacturer manufacturer = manufacturerService.findById(request.getManufacturerId());
            equipment.setManufacturer(manufacturer);
        }

        if (!request.getEquipmentCategoryId().equals(equipment.getEquipmentCategory().getId())) {
            EquipmentCategory category = equipmentCategoryService.findById(request.getEquipmentCategoryId());
            equipment.setEquipmentCategory(category);
        }

        if (!StringUtils.equals(request.getDescription(), equipment.getDescription())) {
            equipment.setDescription(request.getDescription());
        }
    }
}
