package pl.itkurnik.skirental.domain.rent.util;

import pl.itkurnik.skirental.domain.rent.RentStatus;
import pl.itkurnik.skirental.domain.rent.dto.RentStatusInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class RentStatusMapper {

    public static RentStatusInfoDto mapToInfoDto(RentStatus rentStatus) {
        RentStatusInfoDto rentStatusInfoDto = new RentStatusInfoDto();

        rentStatusInfoDto.setId(rentStatus.getId());
        rentStatusInfoDto.setName(rentStatus.getName());
        rentStatusInfoDto.setDescription(rentStatus.getDescription());

        return rentStatusInfoDto;
    }

    public static List<RentStatusInfoDto> mapAllToInfoDto(List<RentStatus> rentStatuses) {
        return rentStatuses.stream()
                .map(RentStatusMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
