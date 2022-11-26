package pl.itkurnik.skirental.util.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ItemsIdsListValidator {

    public static void validate(List<Integer> itemsIds, List<String> errorMessages) {
        if (Objects.isNull(itemsIds) || itemsIds.isEmpty()) {
            errorMessages.add("Items list is empty");
        }

        Set<Integer> uniqueIds = new HashSet<>(itemsIds);
        boolean itemsAreDuplicated = itemsIds.size() != uniqueIds.size();

        if (itemsAreDuplicated) {
            errorMessages.add("Some items are duplicated");
        }
    }
}
