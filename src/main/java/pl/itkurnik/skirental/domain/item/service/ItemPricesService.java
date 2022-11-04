package pl.itkurnik.skirental.domain.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.item.Price;
import pl.itkurnik.skirental.domain.item.repository.PriceRepository;
import pl.itkurnik.skirental.domain.item.validation.ItemExistsValidator;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemPricesService {
    private final PriceRepository priceRepository;
    private final ItemExistsValidator itemExistsValidator;

    public void makeItemPreviousPricesNotActual(Integer itemId, Instant validToTime) {
        itemExistsValidator.validateById(itemId);

        List<Price> itemPrices = priceRepository.findAllByItem_Id(itemId);

        List<Price> actualPrices = itemPrices.stream()
                .filter(price -> Objects.isNull(price.getValidTo()))
                .collect(Collectors.toList());

        actualPrices.forEach(price -> price.setValidTo(validToTime));
        priceRepository.saveAll(actualPrices);
    }

}
