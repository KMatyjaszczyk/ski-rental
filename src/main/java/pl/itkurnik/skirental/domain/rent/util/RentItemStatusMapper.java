package pl.itkurnik.skirental.domain.rent.util;

import pl.itkurnik.skirental.domain.rent.RentItemStatus;
import pl.itkurnik.skirental.domain.rent.dto.RentItemStatusInfoDto;

public class RentItemStatusMapper {

    public static RentItemStatusInfoDto mapToInfoDto(RentItemStatus rentItemStatus) {
        RentItemStatusInfoDto rentItemStatusInfo = new RentItemStatusInfoDto();

        rentItemStatusInfo.setId(rentItemStatus.getId());
        rentItemStatusInfo.setName(rentItemStatus.getName());
        rentItemStatusInfo.setDescription(rentItemStatus.getDescription());

        return rentItemStatusInfo;
    }
}
