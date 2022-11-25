package pl.itkurnik.skirental.domain.rent.validation.returnn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rent.dto.ReturnRentItemRequest;
import pl.itkurnik.skirental.domain.rent.exception.ReturnRentItemsValidationException;
import pl.itkurnik.skirental.domain.rent.validation.RentValidator;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ReturnRentItemValidator {
    private final ReturnRentItemFieldsValidator fieldsValidator;
    private final RentValidator rentValidator;
    private final ItemForReturnRentItemValidator itemValidator;
    private final RentItemForReturnRentItemValidator rentItemValidator;

    public void validateReturnSingleItem(ReturnRentItemRequest request) {
        fieldsValidator.validateFields(request);
        rentValidator.validateIfRentIsInRentedState(request.getRentId());
        itemValidator.validateById(request.getItemId());
        rentItemValidator.validateByRentAndItem(request.getRentId(), request.getItemId());
    }

    public void validateReturnMultipleItems(Integer rentId) {
        validateRentId(rentId);
        rentValidator.validateIfRentIsInRentedState(rentId);
    }

    private void validateRentId(Integer rentId) {
        if (Objects.isNull(rentId)) {
            throw new ReturnRentItemsValidationException("Rent id is null");
        }
    }
}
