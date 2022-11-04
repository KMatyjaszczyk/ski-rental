package pl.itkurnik.skirental.domain.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.item.Item;
import pl.itkurnik.skirental.domain.item.Price;
import pl.itkurnik.skirental.domain.item.dto.CreatePriceRequest;
import pl.itkurnik.skirental.domain.item.exception.ItemNotFoundException;
import pl.itkurnik.skirental.domain.item.repository.ItemRepository;
import pl.itkurnik.skirental.domain.item.repository.PriceRepository;
import pl.itkurnik.skirental.domain.item.util.PriceUtils;
import pl.itkurnik.skirental.domain.item.validation.CreatePriceValidator;
import pl.itkurnik.skirental.domain.item.validation.ItemExistsValidator;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemPricesService {
    private final ItemRepository itemRepository;
    private final PriceRepository priceRepository;
    private final ItemExistsValidator itemExistsValidator;
    private final CreatePriceValidator createPriceValidator;

    public Price findActualPriceForItemByItemId(Integer itemId) {
        itemExistsValidator.validateById(itemId);

        List<Price> prices = priceRepository.findAllByItem_Id(itemId);
        return PriceUtils.findActualPrice(prices, itemId);
    }

    @Transactional
    public void createPriceForItem(CreatePriceRequest request) {
        createPriceValidator.validateFields(request);

        Instant newPriceCreateTime = Instant.now(); // TODO KM there always should be clock in Instant.now() https://youtu.be/PrPQ5xHYa0s
        makeItemPreviousPricesNotActual(request.getItemId(), newPriceCreateTime);
        createPrice(request, newPriceCreateTime);
    }

    private void makeItemPreviousPricesNotActual(Integer itemId, Instant validToTime) {
        itemExistsValidator.validateById(itemId);

        List<Price> itemPrices = priceRepository.findAllByItem_Id(itemId);

        List<Price> actualPrices = itemPrices.stream()
                .filter(price -> Objects.isNull(price.getValidTo()))
                .collect(Collectors.toList());

        actualPrices.forEach(price -> price.setValidTo(validToTime));
        priceRepository.saveAll(actualPrices);
    }

    private void createPrice(CreatePriceRequest request, Instant newPriceCreateTime) {
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new ItemNotFoundException(request.getItemId()));

        Price price = new Price();
        price.setItem(item);
        price.setPriceValue(request.getPriceValue());
        price.setValidFrom(newPriceCreateTime);
        price.setValidTo(null);
        price.setDescription(request.getDescription());

        priceRepository.save(price);
    }
}
