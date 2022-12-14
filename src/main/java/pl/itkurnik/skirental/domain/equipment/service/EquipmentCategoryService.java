package pl.itkurnik.skirental.domain.equipment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.equipment.EquipmentCategory;
import pl.itkurnik.skirental.domain.equipment.Size;
import pl.itkurnik.skirental.domain.equipment.dto.CreateEquipmentCategoryRequest;
import pl.itkurnik.skirental.domain.equipment.dto.SizeForCreateEquipmentCategoryRequest;
import pl.itkurnik.skirental.domain.equipment.dto.UpdateEquipmentCategoryRequest;
import pl.itkurnik.skirental.domain.equipment.exception.EquipmentCategoryNotFoundException;
import pl.itkurnik.skirental.domain.equipment.repository.EquipmentCategoryRepository;
import pl.itkurnik.skirental.domain.equipment.repository.SizeRepository;
import pl.itkurnik.skirental.domain.equipment.validation.CreateEquipmentCategoryValidator;
import pl.itkurnik.skirental.domain.equipment.validation.UpdateEquipmentCategoryValidator;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquipmentCategoryService {
    private final EquipmentCategoryRepository equipmentCategoryRepository;
    private final SizeRepository sizeRepository;
    private final CreateEquipmentCategoryValidator createEquipmentCategoryValidator;
    private final UpdateEquipmentCategoryValidator updateEquipmentCategoryValidator;

    public List<EquipmentCategory> findAll() {
        return equipmentCategoryRepository.findAll();
    }

    public EquipmentCategory findById(Integer id) {
        return equipmentCategoryRepository.findById(id)
                .orElseThrow(() -> new EquipmentCategoryNotFoundException(id));
    }

    public void deleteById(Integer id) {
        try {
            equipmentCategoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignored) {
            log.info("Equipment category with ID {} already deleted", id);
        }
    }

    @Transactional
    public void deleteWithSizesById(Integer id) {
        boolean equipmentCategoryDoesNotExist = !equipmentCategoryRepository.existsById(id);
        if (equipmentCategoryDoesNotExist) {
            log.info("Equipment category with ID {} already deleted", id);
            return;
        }

        sizeRepository.deleteAllByEquipmentCategory_Id(id);
        deleteById(id);
    }

    @Transactional
    public void create(CreateEquipmentCategoryRequest request) {
        createEquipmentCategoryValidator.validate(request);

        EquipmentCategory equipmentCategory = createEquipmentCategory(request);
        createSizesForCategory(request.getSizes(), equipmentCategory);
    }

    private EquipmentCategory createEquipmentCategory(CreateEquipmentCategoryRequest request) {
        EquipmentCategory equipmentCategory = new EquipmentCategory();
        equipmentCategory.setName(request.getName());
        equipmentCategory.setDescription(request.getDescription());

        return equipmentCategoryRepository.save(equipmentCategory);
    }

    private void createSizesForCategory(
            List<SizeForCreateEquipmentCategoryRequest> sizesFromRequest,
            EquipmentCategory equipmentCategory
    ) {
        for (SizeForCreateEquipmentCategoryRequest sizeFromRequest : sizesFromRequest) {
            Size size = new Size();
            size.setSize(sizeFromRequest.getSize());
            size.setEquipmentCategory(equipmentCategory);
            size.setDescription(sizeFromRequest.getDescription());

            sizeRepository.save(size);
        }
    }

    public void update(UpdateEquipmentCategoryRequest request) {
        updateEquipmentCategoryValidator.validate(request);

        EquipmentCategory equipmentCategory = equipmentCategoryRepository.findById(request.getId())
                .orElseThrow(() -> new EquipmentCategoryNotFoundException(request.getId()));
        updateProperFields(request, equipmentCategory);

        equipmentCategoryRepository.save(equipmentCategory);
    }

    private void updateProperFields(UpdateEquipmentCategoryRequest request, EquipmentCategory equipmentCategory) {
        if (!StringUtils.equals(request.getName(), equipmentCategory.getName())) {
            equipmentCategory.setName(request.getName());
        }

        if (!StringUtils.equals(request.getDescription(), equipmentCategory.getName())) {
            equipmentCategory.setDescription(request.getDescription());
        }
    }
}
