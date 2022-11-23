package pl.itkurnik.skirental.domain.rent.validation.returnn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rent.RentItem;
import pl.itkurnik.skirental.domain.rent.dto.ReturnRentItemRequest;
import pl.itkurnik.skirental.domain.rent.service.RentItemService;
import pl.itkurnik.skirental.domain.rent.service.RentItemStatusService;


@Component
@RequiredArgsConstructor
class RentItemForReturnRentItemValidator {
    private final RentItemService rentItemService;
    private final RentItemStatusService rentItemStatusService;
    
    public void validateByRequest(ReturnRentItemRequest request) {
        RentItem rentItem = rentItemService.findByRentIdAndItemId(request.getRentId(), request.getItemId());
        boolean rentItemIsNotRented = !rentItem.getRentItemStatus().getId().equals(
                rentItemStatusService.getRentedStatus().getId());

        if (rentItemIsNotRented) {
            throw new IllegalStateException("Rent item is not rented");
        }
    }
}
