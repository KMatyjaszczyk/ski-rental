package pl.itkurnik.skirental.domain.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.item.Item;
import pl.itkurnik.skirental.domain.item.Price;
import pl.itkurnik.skirental.domain.item.dto.CreatePriceRequest;
import pl.itkurnik.skirental.domain.item.exception.ActualPriceForItemNotFoundException;
import pl.itkurnik.skirental.domain.item.exception.PriceNotFoundException;
import pl.itkurnik.skirental.domain.item.repository.PriceRepository;
import pl.itkurnik.skirental.domain.item.validation.CreatePriceValidator;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PriceService {
    private final PriceRepository priceRepository;
    private final ItemService itemService;
    private final ItemPricesService itemPricesService;
    private final CreatePriceValidator createPriceValidator;

    public Price findById(Integer id) {
        return priceRepository.findById(id)
                .orElseThrow(() -> new PriceNotFoundException(id));
    }

    public Price findActualPriceForItemByItemId(Integer itemId) {
        verifyIfItemExists(itemId);

        List<Price> prices = priceRepository.findAllByItem_Id(itemId);
        return prices.stream()
                .filter(price -> Objects.isNull(price.getValidTo()))
                .findFirst()
                .orElseThrow(() -> new ActualPriceForItemNotFoundException(itemId));
    }

    private void verifyIfItemExists(Integer itemId) {
        itemService.findById(itemId);
    }

    @Transactional
    public void create(CreatePriceRequest request) {
        createPriceValidator.validateFields(request);

        Instant newPriceCreateTime = Instant.now();
        itemPricesService.makeItemPreviousPricesNotActual(request.getItemId(), newPriceCreateTime);
        createPrice(request, newPriceCreateTime);
    }

    private void createPrice(CreatePriceRequest request, Instant newPriceCreateTime) {
        Item item = itemService.findById(request.getItemId());

        Price price = new Price();
        price.setItem(item);
        price.setPriceValue(request.getPriceValue());
        price.setValidFrom(newPriceCreateTime);
        price.setValidTo(null);
        price.setDescription(request.getDescription());

        priceRepository.save(price);
    }
}
