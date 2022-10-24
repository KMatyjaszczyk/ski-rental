package pl.itkurnik.skirental.domain.item.util;

import pl.itkurnik.skirental.domain.item.Price;
import pl.itkurnik.skirental.domain.item.exception.ActualPriceForItemNotFoundException;

import java.util.Collection;
import java.util.Objects;

public class PriceUtils {

    public static Price findActualPrice(Collection<Price> prices, Integer itemId) {
        return prices.stream()
                .filter(price -> Objects.isNull(price.getValidTo()))
                .findFirst()
                .orElseThrow(() -> new ActualPriceForItemNotFoundException(itemId));
    }
}
