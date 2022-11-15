package pl.itkurnik.skirental.domain.equipment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.equipment.Equipment;
import pl.itkurnik.skirental.domain.equipment.EquipmentImage;
import pl.itkurnik.skirental.domain.equipment.dto.CreateEquipmentImageRequest;
import pl.itkurnik.skirental.domain.equipment.dto.ImageUrlModel;
import pl.itkurnik.skirental.domain.equipment.exception.EquipmentImageNotFoundException;
import pl.itkurnik.skirental.domain.equipment.repository.EquipmentImageRepository;
import pl.itkurnik.skirental.domain.equipment.util.EquipmentImageUuidGenerator;
import pl.itkurnik.skirental.domain.equipment.validation.CreateEquipmentImageValidator;
import pl.itkurnik.skirental.s3.S3ImageService;
import pl.itkurnik.skirental.util.MultipartFileUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ImageUrlModel> receiveEquipmentImagesUrls(Integer equipmentId) {
        List<EquipmentImage> equipmentImages = equipmentImageRepository.findAllByEquipment_Id(equipmentId);

        return equipmentImages.stream()
                .map(this::mapEquipmentImageToUrlModel)
                .collect(Collectors.toList());
    }

    private ImageUrlModel mapEquipmentImageToUrlModel(EquipmentImage equipmentImage) {
        ImageUrlModel imageUrl = new ImageUrlModel();

        imageUrl.setId(equipmentImage.getId());
        imageUrl.setEquipmentId(equipmentImage.getEquipment().getId());
        imageUrl.setUrl(s3ImageService.receiveUrl(equipmentImage.getImageUuid()));

        return imageUrl;
    }

    @Transactional
    public void create(CreateEquipmentImageRequest request) {
        createEquipmentImageValidator.validateFields(request);

        Equipment equipment = equipmentService.findById(request.getEquipmentId());
        String imageExtension = MultipartFileUtils.extractExtension(request.getImage());
        String imageUuid = EquipmentImageUuidGenerator.generate(equipment, imageExtension);

        createEquipmentImage(equipment, imageUuid);

        s3ImageService.upload(request.getImage(), imageUuid);
    }

    private void createEquipmentImage(Equipment equipment, String imageUuid) {
        EquipmentImage equipmentImage = new EquipmentImage();

        equipmentImage.setImageUuid(imageUuid);
        equipmentImage.setEquipment(equipment);

        equipmentImageRepository.save(equipmentImage);
    }

    @Transactional
    public void deleteById(Integer id) {
        boolean imageDoesNotExist = !equipmentImageRepository.existsById(id);
        if (imageDoesNotExist) {
            return;
        }

        EquipmentImage image = findById(id);

        equipmentImageRepository.deleteById(id);
        s3ImageService.delete(image.getImageUuid());
    }
}
