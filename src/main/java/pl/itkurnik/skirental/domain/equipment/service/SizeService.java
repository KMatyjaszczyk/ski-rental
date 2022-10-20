package pl.itkurnik.skirental.domain.equipment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.equipment.EquipmentCategory;
import pl.itkurnik.skirental.domain.equipment.Size;
import pl.itkurnik.skirental.domain.equipment.dto.CreateSizeRequest;
import pl.itkurnik.skirental.domain.equipment.dto.UpdateSizeRequest;
import pl.itkurnik.skirental.domain.equipment.exception.SizeNotFoundException;
import pl.itkurnik.skirental.domain.equipment.repository.SizeRepository;
import pl.itkurnik.skirental.domain.equipment.validation.CreateSizeValidator;
import pl.itkurnik.skirental.domain.equipment.validation.UpdateSizeValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SizeService {
    private final SizeRepository sizeRepository;
    private final EquipmentCategoryService equipmentCategoryService;
    private final CreateSizeValidator createSizeValidator;
    private final UpdateSizeValidator updateSizeValidator;

    public Size findById(Integer id) {
        return sizeRepository.findById(id)
                .orElseThrow(() -> new SizeNotFoundException(id));
    }

    public List<Size> findAllByEquipmentCategoryId(Integer equipmentCategoryId) {
        return sizeRepository.findAllByEquipmentCategory_Id(equipmentCategoryId);
    }

    public void deleteById(Integer id) {
        try {
            sizeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignored) {
            log.info("Size with ID {} already deleted", id);
        }
    }

    public void create(CreateSizeRequest request) {
        createSizeValidator.validateFields(request);
        createSize(request);
    }

    private void createSize(CreateSizeRequest request) {
        EquipmentCategory equipmentCategory = equipmentCategoryService.findById(request.getEquipmentCategoryId());

        Size size = new Size();
        size.setSize(request.getSize());
        size.setEquipmentCategory(equipmentCategory);
        size.setDescription(request.getDescription());

        sizeRepository.save(size);
    }

    public void update(UpdateSizeRequest request) {
        updateSizeValidator.validateFields(request);

        Size size = sizeRepository.findById(request.getId())
                .orElseThrow(() -> new SizeNotFoundException(request.getId()));
        updateProperFields(size, request);

        sizeRepository.save(size);
    }

    private void updateProperFields(Size size, UpdateSizeRequest request) {
        if (!request.getSize().equals(size.getSize())) {
            size.setSize(request.getSize());
        }

        if (!request.getEquipmentCategoryId().equals(size.getEquipmentCategory().getId())) {
            EquipmentCategory category = equipmentCategoryService.findById(request.getId());
            size.setEquipmentCategory(category);
        }

        if (!request.getDescription().equals(size.getDescription())) { // TODO fix NullPointerException
            size.setDescription(request.getDescription());
        }
    }
}
