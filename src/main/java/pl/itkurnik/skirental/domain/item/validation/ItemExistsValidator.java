package pl.itkurnik.skirental.domain.item.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.item.exception.ItemNotFoundException;
import pl.itkurnik.skirental.domain.item.repository.ItemRepository;

@Component
@RequiredArgsConstructor
public class ItemExistsValidator {
    private final ItemRepository itemRepository;

    public void validateById(Integer itemId) {
        boolean itemDoesNotExist = !itemRepository.existsById(itemId);
        if (itemDoesNotExist) {
            throw new ItemNotFoundException(itemId);
        }
    }
}
