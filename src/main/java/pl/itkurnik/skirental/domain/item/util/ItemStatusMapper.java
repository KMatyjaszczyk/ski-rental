package pl.itkurnik.skirental.domain.item.util;

import pl.itkurnik.skirental.domain.item.ItemStatus;
import pl.itkurnik.skirental.domain.item.dto.ItemStatusInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class ItemStatusMapper {

    public static ItemStatusInfoDto mapToInfoDto(ItemStatus itemStatus) {
        ItemStatusInfoDto itemStatusInfo = new ItemStatusInfoDto();

        itemStatusInfo.setId(itemStatus.getId());
        itemStatusInfo.setName(itemStatus.getName());
        itemStatusInfo.setDescription(itemStatus.getDescription());

        return itemStatusInfo;
    }

    public static List<ItemStatusInfoDto> mapAllToInfoDto(List<ItemStatus> itemStatuses) {
        return itemStatuses.stream()
                .map(ItemStatusMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
