package pl.itkurnik.skirental.domain.equipment.util;

import pl.itkurnik.skirental.domain.equipment.Manufacturer;
import pl.itkurnik.skirental.domain.equipment.dto.ManufacturerInfoDto;

public class ManufacturerMapper {

    public static ManufacturerInfoDto mapToInfoDto(Manufacturer manufacturer) {
        ManufacturerInfoDto manufacturerInfo = new ManufacturerInfoDto();

        manufacturerInfo.setId(manufacturer.getId());
        manufacturerInfo.setName(manufacturer.getName());
        manufacturerInfo.setDescription(manufacturer.getDescription());

        return manufacturerInfo;
    }
}
