package pl.itkurnik.skirental.domain.equipment.util;

import pl.itkurnik.skirental.domain.equipment.Size;
import pl.itkurnik.skirental.domain.equipment.dto.SizeFlatInfoDto;
import pl.itkurnik.skirental.domain.equipment.dto.SizeInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class SizeMapper {

    public static SizeInfoDto mapToInfoDto(Size size) {
        SizeInfoDto sizeInfo = new SizeInfoDto();

        sizeInfo.setId(size.getId());
        sizeInfo.setSize(size.getSize());
        sizeInfo.setDescription(size.getDescription());

        sizeInfo.setEquipmentCategory(EquipmentCategoryMapper.mapToFlatInfoDto(size.getEquipmentCategory()));

        return sizeInfo;
    }

    public static List<SizeInfoDto> mapAllToInfoDto(List<Size> sizes) {
        return sizes.stream()
                .map(SizeMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }

    public static SizeFlatInfoDto mapToFlatInfoDto(Size size) {
        SizeFlatInfoDto sizeInfo = new SizeFlatInfoDto();

        sizeInfo.setId(size.getId());
        sizeInfo.setSize(size.getSize());
        sizeInfo.setDescription(size.getDescription());

        return sizeInfo;
    }
}
