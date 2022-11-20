package pl.itkurnik.skirental.domain.rent.util;

import pl.itkurnik.skirental.domain.item.util.ItemMapper;
import pl.itkurnik.skirental.domain.rent.RentItem;
import pl.itkurnik.skirental.domain.rent.dto.RentItemInfoDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RentItemMapper {

    public static RentItemInfoDto mapToInfoDto(RentItem rentItem) {
        RentItemInfoDto rentItemInfo = new RentItemInfoDto();

        rentItemInfo.setId(rentItem.getId());
        rentItemInfo.setRentId(rentItem.getRent().getId());
        rentItemInfo.setItem(ItemMapper.mapToInfoDto(rentItem.getItem()));
        rentItemInfo.setRentPrice(rentItem.getRentPrice());
        rentItemInfo.setRentItemStatus(RentItemStatusMapper.mapToInfoDto(rentItem.getRentItemStatus()));
        rentItemInfo.setRentedFrom(rentItem.getRentedFrom());
        rentItemInfo.setRentedTo(rentItem.getRentedTo());
        rentItemInfo.setPaidAmount(rentItem.getPaidAmount());
        rentItemInfo.setDescription(rentItem.getDescription());

        return rentItemInfo;
    }

    public static List<RentItemInfoDto> mapAllToInfoDto(Set<RentItem> rentItems) {
        return rentItems.stream()
                .map(RentItemMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
