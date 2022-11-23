package pl.itkurnik.skirental.domain.rent.validation.returnn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rent.dto.ReturnRentItemsRequest;
import pl.itkurnik.skirental.domain.rent.validation.RentValidator;

@Component
@RequiredArgsConstructor
public class ReturnRentItemsValidator {
    private final RentValidator rentValidator;
    private final ReturnRentItemsFieldsValidator fieldsValidator;
    private final ItemsForReturnRentItemsValidator itemsValidator;
    private final RentItemsForReturnRentItemsValidator rentItemsValidator;

    public void validate(ReturnRentItemsRequest request) {
        rentValidator.validateIfRentExistsById(request.getRentId());
        fieldsValidator.validateFields(request);
        itemsValidator.validateByIds(request.getItemsIds());
        rentItemsValidator.validateByRequest(request);
    }
}
