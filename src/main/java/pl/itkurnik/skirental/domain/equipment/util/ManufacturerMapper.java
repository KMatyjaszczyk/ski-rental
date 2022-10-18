package pl.itkurnik.skirental.domain.equipment.util;

import pl.itkurnik.skirental.domain.equipment.Manufacturer;
import pl.itkurnik.skirental.domain.equipment.dto.ManufacturerInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class ManufacturerMapper {

    public static ManufacturerInfoDto mapToInfoDto(Manufacturer manufacturer) {
        ManufacturerInfoDto manufacturerInfo = new ManufacturerInfoDto();

        manufacturerInfo.setId(manufacturer.getId());
        manufacturerInfo.setName(manufacturer.getName());
        manufacturerInfo.setDescription(manufacturer.getDescription());

        return manufacturerInfo;
    }

    public static List<ManufacturerInfoDto> mapAllToInfoDto(List<Manufacturer> manufacturers) {
        return manufacturers.stream()
                .map(ManufacturerMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
