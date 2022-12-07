package pl.itkurnik.skirental.domain.item.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.itkurnik.skirental.domain.item.Price;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemsRentCostCalculatorTest {

    private ItemsRentCostCalculator calculator;

    @BeforeEach
    public void init() {
        List<Price> prices = preparePrices();
        calculator = new ItemsRentCostCalculator(prices);
    }

    private List<Price> preparePrices() {
        Price firstPrice = Price.builder().priceValue(new BigDecimal("21.37")).build();
        Price secondPrice = Price.builder().priceValue(new BigDecimal("13.37")).build();

        return Arrays.asList(firstPrice, secondPrice);
    }

    @Test
    public void shouldCalculatePriceForOneDay_WhenDateFromAndDateToAreTheSame() {
        String dateFromAndTo = "2022-12-07";
        LocalDate dateFrom = LocalDate.parse(dateFromAndTo);
        LocalDate dateTo = LocalDate.parse(dateFromAndTo);

        BigDecimal result = calculator.calculate(dateFrom, dateTo);

        assertEquals(0, result.compareTo(new BigDecimal(("34.74"))));
    }

    @Test
    public void shouldCalculatePriceForMultipleDays() {
        LocalDate dateFrom = LocalDate.parse("2022-12-07");
        LocalDate dateTo = LocalDate.parse("2022-12-10");

        BigDecimal result = calculator.calculate(dateFrom, dateTo);

        assertEquals(0, result.compareTo(new BigDecimal(("138.96"))));
    }

    @Test
    public void shouldThrowAnException_WhenDateFromIsAfterDateTo() {
        LocalDate dateFrom = LocalDate.parse("2022-12-07");
        LocalDate dateTo = LocalDate.parse("2022-12-06");

        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(dateFrom, dateTo));
    }

    @Test
    public void shouldCalculateZero_WhenThereAreNoPrices() {
        LocalDate dateFrom = LocalDate.parse("2022-12-07");
        LocalDate dateTo = LocalDate.parse("2022-12-10");
        calculator = new ItemsRentCostCalculator(Collections.emptyList());

        BigDecimal result = calculator.calculate(dateFrom, dateTo);

        assertEquals(0, result.compareTo(new BigDecimal(("0.00"))));
    }
}