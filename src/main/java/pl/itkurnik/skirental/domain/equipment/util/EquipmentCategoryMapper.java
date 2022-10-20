package pl.itkurnik.skirental.domain.equipment.util;

import pl.itkurnik.skirental.domain.equipment.EquipmentCategory;
import pl.itkurnik.skirental.domain.equipment.dto.EquipmentCategoryFlatInfoDto;
import pl.itkurnik.skirental.domain.equipment.dto.EquipmentCategoryInfoDto;
import pl.itkurnik.skirental.domain.equipment.dto.SizeFlatInfoDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EquipmentCategoryMapper {

    public static EquipmentCategoryInfoDto mapToInfoDto(EquipmentCategory equipmentCategory) {
        EquipmentCategoryInfoDto equipmentCategoryInfo = new EquipmentCategoryInfoDto();

        equipmentCategoryInfo.setId(equipmentCategory.getId());
        equipmentCategoryInfo.setName(equipmentCategory.getName());
        equipmentCategoryInfo.setDescription(equipmentCategory.getDescription());

        Set<SizeFlatInfoDto> sizeInfos = equipmentCategory.getSizes().stream()
                .map(SizeMapper::mapToFlatInfoDto)
                .collect(Collectors.toSet());
        equipmentCategoryInfo.setSizes(sizeInfos);

        return equipmentCategoryInfo;
    }

    public static List<EquipmentCategoryInfoDto> mapAllToInfoDto(List<EquipmentCategory> equipmentCategories) {
        return equipmentCategories.stream()
                .map(EquipmentCategoryMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }

    public static EquipmentCategoryFlatInfoDto mapToFlatInfoDto(EquipmentCategory equipmentCategory) {
        EquipmentCategoryFlatInfoDto equipmentCategoryFlatInfo = new EquipmentCategoryFlatInfoDto();

        equipmentCategoryFlatInfo.setId(equipmentCategory.getId());
        equipmentCategoryFlatInfo.setName(equipmentCategory.getName());
        equipmentCategoryFlatInfo.setDescription(equipmentCategory.getDescription());

        return equipmentCategoryFlatInfo;
    }

    private static List<EquipmentCategoryFlatInfoDto> mapAllToFlatInfoDto(List<EquipmentCategory> equipmentCategories) {
        return equipmentCategories.stream()
                .map(EquipmentCategoryMapper::mapToFlatInfoDto)
                .collect(Collectors.toList());
    }
}
