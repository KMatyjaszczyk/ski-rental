package pl.itkurnik.skirental.domain.rent.service;

import lombok.RequiredArgsConstructor;
import pl.itkurnik.skirental.domain.rent.RentItem;
import pl.itkurnik.skirental.util.RentTimeCalculator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;

@RequiredArgsConstructor
public class RentCostCalculator {
    private final Collection<RentItem> rentItems;
    private final ZoneId zoneId;

    public BigDecimal calculate() {
        BigDecimal rentSummaryCost = BigDecimal.ZERO;
        for (RentItem rentItem : rentItems) {
            BigDecimal costForItem = calculateForSingleItem(rentItem);
            rentSummaryCost = rentSummaryCost.add(costForItem);
        }

        return rentSummaryCost;
    }

    private BigDecimal calculateForSingleItem(RentItem rentItem) {
        Long itemRentDurationInDays = calculateItemRentDurationInDays(rentItem);
        BigDecimal price = rentItem.getRentPrice();

        return price.multiply(BigDecimal.valueOf(itemRentDurationInDays));
    }

    private Long calculateItemRentDurationInDays(RentItem rentItem) {
        LocalDate dateFrom = rentItem.getRentedFrom()
                .atZone(zoneId).toLocalDate();
        LocalDate dateTo = rentItem.getRentedTo()
                .atZone(zoneId).toLocalDate();

        return RentTimeCalculator.calculateNumberOfDaysForRent(dateFrom, dateTo);
    }
}
