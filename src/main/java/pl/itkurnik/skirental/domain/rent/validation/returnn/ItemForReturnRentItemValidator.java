package pl.itkurnik.skirental.domain.rent.validation.returnn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.item.Item;
import pl.itkurnik.skirental.domain.item.service.ItemService;
import pl.itkurnik.skirental.domain.item.service.ItemStatusService;

@Component
@RequiredArgsConstructor
class ItemForReturnRentItemValidator {
    private final ItemService itemService;
    private final ItemStatusService itemStatusService;

    public void validateById(Integer id) {
        Item item = itemService.findById(id);
        boolean itemIsNotRented = !item.getItemStatus().getId().equals(
                itemStatusService.getRentedStatus().getId());

        if (itemIsNotRented) {
            throw new IllegalStateException("Item is not rented");
        }
    }
}
