package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.item.Item;
import pl.itkurnik.skirental.domain.item.service.ItemPricesService;
import pl.itkurnik.skirental.domain.item.service.ItemService;
import pl.itkurnik.skirental.domain.rent.Rent;
import pl.itkurnik.skirental.domain.rent.RentItem;
import pl.itkurnik.skirental.domain.rent.repository.RentItemRepository;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentItemsCreator {
    private final RentItemRepository rentItemRepository;
    private final ItemService itemService;
    private final ItemPricesService itemPricesService;
    private final RentItemStatusService rentItemStatusService;

    public void createFromItemsByIds(List<Integer> itemsIds, Rent savedRent, Instant rentCreateTime) {
        List<Item> items = mapIdsFromRequestToItems(itemsIds);
        Set<RentItem> rentItems = mapItemsToRentItems(items, savedRent, rentCreateTime);

        rentItemRepository.saveAll(rentItems);
    }

    private List<Item> mapIdsFromRequestToItems(List<Integer> itemsIds) {
        return itemsIds.stream()
                .map(itemService::findById)
                .collect(Collectors.toList());
    }

    private Set<RentItem> mapItemsToRentItems(List<Item> items, Rent savedRent, Instant rentCreateTime) {
        return items.stream()
                .map(item ->
                        mapItemToRentItem(item, savedRent, rentCreateTime))
                .collect(Collectors.toSet());
    }

    private RentItem mapItemToRentItem(Item item, Rent savedRent, Instant rentCreateTime) {
        RentItem rentItem = new RentItem();

        rentItem.setRent(savedRent);
        rentItem.setItem(item);
        rentItem.setRentPrice(itemPricesService.findActualPriceForItemByItemId(item.getId()).getPriceValue());
        rentItem.setRentItemStatus(rentItemStatusService.getRentedStatus());
        rentItem.setRentedFrom(rentCreateTime);

        return rentItem;
    }
}
