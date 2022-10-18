package pl.itkurnik.skirental.domain.equipment.util;

import pl.itkurnik.skirental.domain.equipment.EquipmentCategory;
import pl.itkurnik.skirental.domain.equipment.dto.EquipmentCategoryInfoDto;
import pl.itkurnik.skirental.domain.equipment.dto.SizeInfoDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EquipmentCategoryMapper {

    public static EquipmentCategoryInfoDto mapToInfoDto(EquipmentCategory equipmentCategory) {
        EquipmentCategoryInfoDto equipmentCategoryInfo = new EquipmentCategoryInfoDto();

        equipmentCategoryInfo.setId(equipmentCategory.getId());
        equipmentCategoryInfo.setName(equipmentCategory.getName());
        equipmentCategoryInfo.setDescription(equipmentCategory.getDescription());

        Set<SizeInfoDto> sizeInfos = equipmentCategory.getSizes().stream()
                .map(SizeMapper::mapToInfoDto)
                .collect(Collectors.toSet());
        equipmentCategoryInfo.setSizes(sizeInfos);

        return equipmentCategoryInfo;
    }

    public static List<EquipmentCategoryInfoDto> mapAllToInfoDto(List<EquipmentCategory> equipmentCategories) {
        return equipmentCategories.stream()
                .map(EquipmentCategoryMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
