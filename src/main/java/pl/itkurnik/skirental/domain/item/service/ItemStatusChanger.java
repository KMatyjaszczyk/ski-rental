package pl.itkurnik.skirental.domain.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.item.Item;
import pl.itkurnik.skirental.domain.item.ItemStatus;
import pl.itkurnik.skirental.domain.item.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemStatusChanger {
    private final ItemRepository itemRepository;
    private final ItemStatusService itemStatusService;

    public void changeAllToOpen(List<Integer> itemIds) {
        changeItemsStatus(itemIds, itemStatusService.getOpenStatus());
    }

    public void changeAllToRented(List<Integer> itemsIds) {
        changeItemsStatus(itemsIds, itemStatusService.getRentedStatus());
    }

    private void changeItemsStatus(List<Integer> itemsIds, ItemStatus statusToModify) {
        List<Item> itemsToModify = itemRepository.findAllByIdIn(itemsIds);

        itemsToModify.forEach(item ->
                item.setItemStatus(statusToModify));

        itemRepository.saveAll(itemsToModify);
    }

}
