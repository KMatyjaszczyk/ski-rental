package pl.itkurnik.skirental.domain.item.service;

import lombok.RequiredArgsConstructor;
import pl.itkurnik.skirental.domain.item.Price;
import pl.itkurnik.skirental.util.RentTimeCalculator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
public class ItemsRentCostCalculator {
    private final List<Price> itemPrices;

    public BigDecimal calculate(LocalDate dateFrom, LocalDate dateTo) {
        long rentDurationInDays = RentTimeCalculator.calculateNumberOfDaysForRent(dateFrom, dateTo);

        BigDecimal rentSummaryCost = BigDecimal.ZERO;
        for (Price itemPrice : itemPrices) {
            BigDecimal costForItem = calculateForSingleItem(itemPrice, rentDurationInDays);
            rentSummaryCost = rentSummaryCost.add(costForItem);
        }

        return rentSummaryCost;
    }

    private BigDecimal calculateForSingleItem(Price itemPrice, long rentDurationInDays) {
        BigDecimal priceValue = itemPrice.getPriceValue();

        return priceValue.multiply(BigDecimal.valueOf(rentDurationInDays));
    }
}
