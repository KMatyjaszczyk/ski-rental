package pl.itkurnik.skirental.domain.rent.validation.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rent.dto.CreateRentRequest;

@Component
@RequiredArgsConstructor
public class CreateRentValidator {
    private final CreateRentFieldsValidator fieldsValidator;
    private final ItemsForCreateRentValidator itemsValidator;
    private final UsersForRentValidator usersValidator;

    public void validateRequest(CreateRentRequest request) {
        fieldsValidator.validateFields(request);
        itemsValidator.validateByIds(request.getRentItemsIds());
        usersValidator.validate(request);
    }
}
