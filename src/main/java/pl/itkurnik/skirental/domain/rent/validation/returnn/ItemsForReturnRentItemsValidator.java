package pl.itkurnik.skirental.domain.rent.validation.returnn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.item.Item;
import pl.itkurnik.skirental.domain.item.service.ItemService;
import pl.itkurnik.skirental.domain.item.service.ItemStatusService;
import pl.itkurnik.skirental.domain.rent.exception.ItemsForRentNotFoundException;

import java.util.List;

@Component
@RequiredArgsConstructor
class ItemsForReturnRentItemsValidator {
    private final ItemService itemService;
    private final ItemStatusService itemStatusService;

    public void validateByIds(List<Integer> ids) {
        List<Item> items = itemService.findAllByIds(ids);

        validateIfAllItemsExist(ids, items);
        validateIfAllItemsAreRented(items);
    }

    private void validateIfAllItemsExist(List<Integer> itemsIds, List<Item> items) {
        boolean notAllItemsWereFound = itemsIds.size() > items.size();

        if (notAllItemsWereFound) {
            throw new ItemsForRentNotFoundException();
        }
    }

    private void validateIfAllItemsAreRented(List<Item> items) {
        boolean notAllItemsAreRented = items.stream()
                .anyMatch(item ->
                        !item.getItemStatus().getId().equals(
                                itemStatusService.getRentedStatus().getId()));

        if (notAllItemsAreRented) {
            throw new IllegalStateException("Not all items are rented");
        }
    }
}
