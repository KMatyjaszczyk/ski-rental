package pl.itkurnik.skirental.domain.rent.validation.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.item.Item;
import pl.itkurnik.skirental.domain.item.service.ItemService;
import pl.itkurnik.skirental.domain.item.service.ItemStatusService;
import pl.itkurnik.skirental.domain.rent.exception.ItemsForRentNotFoundException;

import java.util.List;

@Component
@RequiredArgsConstructor
class ItemsForCreateRentValidator {
    private final ItemService itemService;
    private final ItemStatusService itemStatusService;

    public void validateByIds(List<Integer> ids) {
        validateIfAllItemsExist(ids);
        validateIfAllItemsAreOpen(ids);
    }

    private void validateIfAllItemsExist(List<Integer> itemsIds) {
        List<Item> itemsFetched = itemService.findAllByIds(itemsIds);

        boolean notAllItemsWereFound = itemsIds.size() > itemsFetched.size();
        if (notAllItemsWereFound) {
            throw new ItemsForRentNotFoundException();
        }
    }

    private void validateIfAllItemsAreOpen(List<Integer> itemsIds) {
        List<Item> items = itemService.findAllByIds(itemsIds);

        boolean allItemsAreOpen = items.stream()
                .allMatch(item ->
                        item.getItemStatus().getId().equals(itemStatusService.getOpenStatus().getId()));
        if (!allItemsAreOpen) {
            throw new IllegalStateException("Not all items are open for rent");
        }
    }
}
