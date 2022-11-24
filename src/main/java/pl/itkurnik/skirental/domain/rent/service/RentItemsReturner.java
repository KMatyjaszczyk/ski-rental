package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.rent.dto.ReturnRentItemRequest;
import pl.itkurnik.skirental.domain.rent.validation.returnn.ReturnRentItemValidator;

import java.time.Instant;

@Service
@RequiredArgsConstructor
class RentItemsReturner {
    private final ReturnRentItemValidator returnRentItemValidator;
    private final RentItemService rentItemService;

    public void returnItem(ReturnRentItemRequest request, Instant finishDate) {
        returnRentItemValidator.validate(request);

        rentItemService.returnRentItem(request, finishDate);
    }
}
