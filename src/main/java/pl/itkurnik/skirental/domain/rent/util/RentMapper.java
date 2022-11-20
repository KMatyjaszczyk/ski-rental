package pl.itkurnik.skirental.domain.rent.util;

import pl.itkurnik.skirental.domain.rent.Rent;
import pl.itkurnik.skirental.domain.rent.dto.RentInfoDto;
import pl.itkurnik.skirental.domain.user.util.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

public class RentMapper {

    public static RentInfoDto mapToInfoDto(Rent rent) {
        RentInfoDto rentInfo = new RentInfoDto();

        rentInfo.setId(rent.getId());
        rentInfo.setRentDate(rent.getRentDate());
        rentInfo.setPlannedReturnDate(rent.getPlannedReturnDate());
        rentInfo.setEndDate(rent.getEndDate());
        rentInfo.setClient(UserMapper.mapToInfoDto(rent.getClient()));
        rentInfo.setRentStatus(RentStatusMapper.mapToInfoDto(rent.getRentStatus()));
        rentInfo.setClientDocumentType(ClientDocumentTypeMapper.mapToInfoDto(rent.getClientDocumentType()));
        rentInfo.setClientDocumentNumber(rent.getClientDocumentNumber());
        rentInfo.setEmployee(UserMapper.mapToInfoDto(rent.getEmployee()));
        rentInfo.setDescription(rent.getDescription());
        rentInfo.setRentItems(RentItemMapper.mapAllToInfoDto(rent.getRentItems()));

        return rentInfo;
    }

    public static List<RentInfoDto> mapAllToInfoDto(List<Rent> rents) {
        return rents.stream()
                .map(RentMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
