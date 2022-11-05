package pl.itkurnik.skirental.domain.equipment.util;

import pl.itkurnik.skirental.domain.equipment.EquipmentImage;
import pl.itkurnik.skirental.domain.equipment.dto.EquipmentImageInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class EquipmentImageMapper {

    public static EquipmentImageInfoDto mapToInfoDto(EquipmentImage equipmentImage) {
        EquipmentImageInfoDto equipmentImageInfo = new EquipmentImageInfoDto();

        equipmentImageInfo.setId(equipmentImage.getId());
        equipmentImageInfo.setImageUuid(equipmentImage.getImageUuid());
        equipmentImageInfo.setEquipmentId(equipmentImage.getEquipment().getId());
        equipmentImageInfo.setDescription(equipmentImage.getDescription());

        return equipmentImageInfo;
    }

    public static List<EquipmentImageInfoDto> mapAllToInfoDto(List<EquipmentImage> equipmentImages) {
        return equipmentImages.stream()
                .map(EquipmentImageMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
