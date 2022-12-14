package pl.itkurnik.skirental.domain.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.item.Item;
import pl.itkurnik.skirental.domain.item.ItemStatus;
import pl.itkurnik.skirental.domain.item.exception.ItemNotFoundException;
import pl.itkurnik.skirental.domain.item.repository.ItemRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemStatusChanger {
    private final ItemRepository itemRepository;
    private final ItemStatusService itemStatusService;

    public void changeToOpen(Integer itemId) {
        changeItemStatus(itemId, itemStatusService.getOpenStatus());
    }

    private void changeItemStatus(Integer itemId, ItemStatus statusToModify) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException(itemId));

        item.setItemStatus(statusToModify);

        itemRepository.save(item);
    }

    public void changeToBroken(Integer itemId) {
        changeItemStatus(itemId, itemStatusService.getBrokenStatus());
    }

    public void changeToInService(Integer itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException(itemId));

        item.setLastServiceDate(LocalDate.now());
        item.setItemStatus(itemStatusService.getInServiceStatus());

        itemRepository.save(item);
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

    public void changeAllToOpen(List<Integer> itemsIds) {
        changeItemsStatus(itemsIds, itemStatusService.getOpenStatus());
    }
}
