package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.rent.dto.ReturnRentItemsRequest;
import pl.itkurnik.skirental.domain.rent.validation.returnn.ReturnRentItemsValidator;

@Service
@RequiredArgsConstructor
class RentItemsReturner {
    private final ReturnRentItemsValidator returnRentItemsValidator;
    private final RentItemService rentItemService;

    public void returnItems(ReturnRentItemsRequest request) {
        returnRentItemsValidator.validate(request);

        makeRentItemsReturned(request);
    }

    private void makeRentItemsReturned(ReturnRentItemsRequest request) {
        rentItemService.returnRentItems(request);
    }
}
