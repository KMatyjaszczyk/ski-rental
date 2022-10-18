package pl.itkurnik.skirental.domain.equipment.util;

import pl.itkurnik.skirental.domain.equipment.dto.SizeInfoDto;
import pl.itkurnik.skirental.domain.item.Size;

public class SizeMapper {

    public static SizeInfoDto mapToInfoDto(Size size) {
        SizeInfoDto sizeInfo = new SizeInfoDto();

        sizeInfo.setId(size.getId());
        sizeInfo.setSize(size.getSize());
        sizeInfo.setDescription(size.getDescription());

        return sizeInfo;
    }
}
