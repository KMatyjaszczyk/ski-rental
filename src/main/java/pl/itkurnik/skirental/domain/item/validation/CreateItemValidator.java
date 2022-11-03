package pl.itkurnik.skirental.domain.item.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.equipment.Equipment;
import pl.itkurnik.skirental.domain.equipment.Size;
import pl.itkurnik.skirental.domain.equipment.service.EquipmentService;
import pl.itkurnik.skirental.domain.equipment.service.SizeService;
import pl.itkurnik.skirental.domain.item.dto.CreateItemRequest;
import pl.itkurnik.skirental.domain.item.dto.PriceForCreateItemRequest;
import pl.itkurnik.skirental.domain.item.exception.CreateItemValidationException;
import pl.itkurnik.skirental.util.validation.MultipleFieldsValidator;
import pl.itkurnik.skirental.util.validation.NegativeBigDecimalValidator;
import pl.itkurnik.skirental.util.validation.NullObjectValidator;
import pl.itkurnik.skirental.util.validation.ValidationException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CreateItemValidator extends MultipleFieldsValidator<CreateItemRequest> {
    private final EquipmentService equipmentService;
    private final SizeService sizeService;

    @Override
    protected void processFieldsValidation(CreateItemRequest request, List<String> errorMessages) {
        NullObjectValidator.validate(request.getEquipmentId(), "Equipment id", errorMessages);
        NullObjectValidator.validate(request.getSizeId(), "Size id", errorMessages);
        validateLastServiceDateIfNecessary(request.getLastServiceDate(), errorMessages);
        NegativeBigDecimalValidator.validate(request.getPurchasePrice(), "Purchase price", errorMessages);
        validateDefaultPrice(request, errorMessages);
    }

    private void validateLastServiceDateIfNecessary(LocalDate lastServiceDate, List<String> errorMessages) {
        if (!Objects.isNull(lastServiceDate)) {
            validateIfLastServiceDateIsFromTheFuture(lastServiceDate, errorMessages);
        }
    }

    private void validateIfLastServiceDateIsFromTheFuture(LocalDate lastServiceDate, List<String> errorMessages) {
        if (lastServiceDate.isAfter(LocalDate.now())) {
            errorMessages.add("Last service date is from the future");
        }
    }

    private void validateDefaultPrice(CreateItemRequest request, List<String> errorMessages) {
        PriceForCreateItemRequest defaultPriceFromRequest = request.getDefaultPrice();

        if (Objects.isNull(defaultPriceFromRequest)) {
            errorMessages.add("Default price is null");
            return;
        }

        NegativeBigDecimalValidator.validate(defaultPriceFromRequest.getPriceValue(), "Default price's value", errorMessages);
    }

    public void validateIfSizeIsInProperEquipmentCategory(CreateItemRequest request) {
        Equipment equipment = equipmentService.findById(request.getEquipmentId());
        Size size = sizeService.findById(request.getSizeId());

        Integer sizeEquipmentCategory = size.getEquipmentCategory().getId();
        Integer equipmentEquipmentCategory = equipment.getEquipmentCategory().getId();

        boolean categoriesAreNotTheSame = sizeEquipmentCategory.compareTo(equipmentEquipmentCategory) != 0;
        if (categoriesAreNotTheSame) {
            throw new CreateItemValidationException("Size and equipment categories are not the same");
        }
    }

    @Override
    protected Class<? extends ValidationException> getValidationExceptionType() {
        return CreateItemValidationException.class;
    }
}
