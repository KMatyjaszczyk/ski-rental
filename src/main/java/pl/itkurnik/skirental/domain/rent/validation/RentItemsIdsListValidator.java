package pl.itkurnik.skirental.domain.rent.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class RentItemsIdsListValidator {

    public static void validate(List<Integer> rentItemsIds, List<String> errorMessages) {
        if (Objects.isNull(rentItemsIds) || rentItemsIds.isEmpty()) {
            errorMessages.add("Items list is empty");
        }

        Set<Integer> uniqueIds = new HashSet<>(rentItemsIds);
        boolean itemsAreDuplicated = rentItemsIds.size() != uniqueIds.size();

        if (itemsAreDuplicated) {
            errorMessages.add("Some items are duplicated");
        }
    }
}
