package pl.itkurnik.skirental.domain.equipment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.equipment.Equipment;
import pl.itkurnik.skirental.domain.equipment.EquipmentImage;
import pl.itkurnik.skirental.domain.equipment.dto.CreateEquipmentImageRequest;
import pl.itkurnik.skirental.domain.equipment.exception.EquipmentImageNotFoundException;
import pl.itkurnik.skirental.domain.equipment.repository.EquipmentImageRepository;
import pl.itkurnik.skirental.domain.equipment.util.EquipmentImageUuidGenerator;
import pl.itkurnik.skirental.domain.equipment.validation.CreateEquipmentImageValidator;
import pl.itkurnik.skirental.s3.S3ImageService;
import pl.itkurnik.skirental.util.MultipartFileUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentImageService {
    private final EquipmentImageRepository equipmentImageRepository;
    private final EquipmentService equipmentService;
    private final S3ImageService s3ImageService;
    private final CreateEquipmentImageValidator createEquipmentImageValidator;

    public EquipmentImage findById(Integer id) {
        return equipmentImageRepository.findById(id)
                .orElseThrow(() -> new EquipmentImageNotFoundException(id));
    }

    public List<EquipmentImage> findAllByEquipmentId(Integer equipmentId) {
        return equipmentImageRepository.findAllByEquipment_Id(equipmentId);
    }

    public void create(CreateEquipmentImageRequest request) {
        createEquipmentImageValidator.validateFields(request);

        Equipment equipment = equipmentService.findById(request.getEquipmentId());
        String imageExtension = MultipartFileUtils.extractExtension(request.getImage());
        String imageUuid = EquipmentImageUuidGenerator.generate(equipment, imageExtension);

        createEquipmentImage(equipment, imageUuid);

        s3ImageService.putImage(request.getImage(), imageUuid);
    }

    private void createEquipmentImage(Equipment equipment, String imageUuid) {
        EquipmentImage equipmentImage = new EquipmentImage();

        equipmentImage.setImageUuid(imageUuid);
        equipmentImage.setEquipment(equipment);

        equipmentImageRepository.save(equipmentImage);
    }
}
