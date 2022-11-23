package pl.itkurnik.skirental.domain.rent.validation.returnn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rent.RentItem;
import pl.itkurnik.skirental.domain.rent.dto.ReturnRentItemsRequest;
import pl.itkurnik.skirental.domain.rent.exception.ItemsForRentNotFoundException;
import pl.itkurnik.skirental.domain.rent.service.RentItemService;
import pl.itkurnik.skirental.domain.rent.service.RentItemStatusService;

import java.util.List;

@Component
@RequiredArgsConstructor
class RentItemsForReturnRentItemsValidator {
    private final RentItemService rentItemService;
    private final RentItemStatusService rentItemStatusService;
    
    public void validateByRequest(ReturnRentItemsRequest request) {
        List<RentItem> rentItems = rentItemService.findAllByRentIdAndItemsIds(request.getRentId(), request.getItemsIds());
        
        validateIfAllRentItemsExist(request.getItemsIds(), rentItems);
        validateIfAllRentItemsAreRented(rentItems);
    }

    private void validateIfAllRentItemsExist(List<Integer> rentItemsIds, List<RentItem> rentItems) {
        boolean notAllRentItemsWereFound = rentItemsIds.size() > rentItems.size();
        
        if (notAllRentItemsWereFound) {
            throw new ItemsForRentNotFoundException();
        }
    }

    private void validateIfAllRentItemsAreRented(List<RentItem> rentItems) {
        boolean notAllRentItemsAreRented = rentItems.stream()
                .anyMatch(rentItem ->
                        !rentItem.getRentItemStatus().getId().equals(
                                rentItemStatusService.getRentedStatus().getId()));

        if (notAllRentItemsAreRented) {
            throw new IllegalStateException("Not all rent items are rented");
        }
    }
}
