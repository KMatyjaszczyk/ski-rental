package pl.itkurnik.skirental.domain.item.util;

import pl.itkurnik.skirental.domain.equipment.util.EquipmentMapper;
import pl.itkurnik.skirental.domain.equipment.util.SizeMapper;
import pl.itkurnik.skirental.domain.item.Item;
import pl.itkurnik.skirental.domain.item.dto.ItemInfoDto;

import java.util.List;
import java.util.stream.Collectors;

public class ItemMapper {

    public static ItemInfoDto mapToInfoDto(Item item) {
        ItemInfoDto itemInfo = new ItemInfoDto();

        itemInfo.setId(item.getId());
        itemInfo.setEquipment(EquipmentMapper.mapToFlatInfoDto(item.getEquipment()));
        itemInfo.setSize(SizeMapper.mapToFlatInfoDto(item.getSize()));
        itemInfo.setItemStatus(ItemStatusMapper.mapToInfoDto(item.getItemStatus()));
        itemInfo.setLastServiceDate(item.getLastServiceDate());
        itemInfo.setPurchasePrice(item.getPurchasePrice());
        itemInfo.setDescription(item.getDescription());

        return itemInfo;
    }

    public static List<ItemInfoDto> mapAllToInfoDto (List<Item> items) {
        return items.stream()
                .map(ItemMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
