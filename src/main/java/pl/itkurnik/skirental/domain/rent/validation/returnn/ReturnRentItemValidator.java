package pl.itkurnik.skirental.domain.rent.validation.returnn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rent.dto.ReturnRentItemRequest;
import pl.itkurnik.skirental.domain.rent.validation.RentValidator;

@Component
@RequiredArgsConstructor
public class ReturnRentItemValidator {
    private final ReturnRentItemsFieldsValidator fieldsValidator;
    private final RentValidator rentValidator;
    private final ItemForReturnRentItemValidator itemValidator;
    private final RentItemForReturnRentItemValidator rentItemValidator;

    public void validate(ReturnRentItemRequest request) {
        fieldsValidator.validateFields(request);
        rentValidator.validateIfRentExistsById(request.getRentId());
        itemValidator.validateById(request.getItemId());
        rentItemValidator.validateByRequest(request);
    }
}
