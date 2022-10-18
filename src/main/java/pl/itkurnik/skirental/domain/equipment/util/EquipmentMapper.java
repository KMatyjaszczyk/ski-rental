package pl.itkurnik.skirental.domain.equipment.util;

import pl.itkurnik.skirental.domain.equipment.Equipment;
import pl.itkurnik.skirental.domain.equipment.dto.EquipmentInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class EquipmentMapper {

    public static EquipmentInfoDto mapToInfoDto(Equipment equipment) {
        EquipmentInfoDto equipmentInfo = new EquipmentInfoDto();

        equipmentInfo.setId(equipment.getId());
        equipmentInfo.setModel(equipment.getModel());
        equipmentInfo.setManufacturer(ManufacturerMapper.mapToInfoDto(
                equipment.getManufacturer()));
        equipmentInfo.setEquipmentCategory(EquipmentCategoryMapper.mapToInfoDto(
                equipment.getEquipmentCategory()));
        equipmentInfo.setDescription(equipment.getDescription());

        return equipmentInfo;
    }

    public static List<EquipmentInfoDto> mapAllToInfoDto(List<Equipment> equipments) {
        return equipments.stream()
                .map(EquipmentMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
