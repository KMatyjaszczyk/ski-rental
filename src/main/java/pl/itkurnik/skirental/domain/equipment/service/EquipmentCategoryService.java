package pl.itkurnik.skirental.domain.equipment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.equipment.EquipmentCategory;
import pl.itkurnik.skirental.domain.equipment.dto.CreateEquipmentCategoryRequest;
import pl.itkurnik.skirental.domain.equipment.dto.EquipmentCategoryInfoDto;
import pl.itkurnik.skirental.domain.equipment.dto.UpdateEquipmentCategoryRequest;
import pl.itkurnik.skirental.domain.equipment.exception.EquipmentCategoryNotFoundException;
import pl.itkurnik.skirental.domain.equipment.repository.EquipmentCategoryRepository;
import pl.itkurnik.skirental.domain.equipment.util.EquipmentCategoryMapper;
import pl.itkurnik.skirental.domain.equipment.validation.CreateEquipmentCategoryValidator;
import pl.itkurnik.skirental.domain.equipment.validation.UpdateEquipmentCategoryValidator;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquipmentCategoryService {
    private final EquipmentCategoryRepository equipmentCategoryRepository;
    private final CreateEquipmentCategoryValidator createEquipmentCategoryValidator;
    private final UpdateEquipmentCategoryValidator updateEquipmentCategoryValidator;

    public List<EquipmentCategoryInfoDto> findAll() {
        List<EquipmentCategory> equipmentCategories = equipmentCategoryRepository.findAll();

        return equipmentCategories.stream()
                .map(EquipmentCategoryMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }

    public EquipmentCategoryInfoDto findById(Integer id) {
        EquipmentCategory equipmentCategory = equipmentCategoryRepository.findById(id)
                .orElseThrow(() -> new EquipmentCategoryNotFoundException(id));

        return EquipmentCategoryMapper.mapToInfoDto(equipmentCategory);
    }

    public void deleteById(Integer id) {
        try {
            equipmentCategoryRepository.deleteById(id); // TODO KM preferred will be setting flag 'deleted'
        } catch (EmptyResultDataAccessException ignored) {
            log.info("Equipment category with ID {} already deleted", id);
        }
    }

    public void create(CreateEquipmentCategoryRequest request) {
        createEquipmentCategoryValidator.validate(request);
        createEquipmentCategory(request);
    }

    private void createEquipmentCategory(CreateEquipmentCategoryRequest request) {
        EquipmentCategory equipmentCategory = new EquipmentCategory();
        equipmentCategory.setName(request.getName());
        equipmentCategory.setDescription(request.getDescription());
        equipmentCategoryRepository.save(equipmentCategory);
    }

    public void update(UpdateEquipmentCategoryRequest request) {
        updateEquipmentCategoryValidator.validate(request);

        EquipmentCategory equipmentCategory = equipmentCategoryRepository.findById(request.getId())
                .orElseThrow(() -> new EquipmentCategoryNotFoundException(request.getId()));
        updateProperFields(request, equipmentCategory);

        equipmentCategoryRepository.save(equipmentCategory);
    }

    private void updateProperFields(UpdateEquipmentCategoryRequest request, EquipmentCategory equipmentCategory) {
        if (!request.getName().equals(equipmentCategory.getName())) {
            equipmentCategory.setName(request.getName());
        }

        if (!request.getDescription().equals(equipmentCategory.getName())) {
            equipmentCategory.setDescription(request.getDescription());
        }
    }
}
