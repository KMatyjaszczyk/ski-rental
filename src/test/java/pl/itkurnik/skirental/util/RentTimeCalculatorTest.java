package pl.itkurnik.skirental.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RentTimeCalculatorTest {

    @Test
    public void shouldThrowAnException_WhenDateFromIsAfterDateTo() {
        LocalDate dateFrom = LocalDate.parse("2022-12-07");
        LocalDate dateTo = LocalDate.parse("2022-12-06");

        assertThrows(IllegalArgumentException.class,
                () -> RentTimeCalculator.calculateNumberOfDaysForRent(dateFrom, dateTo));
    }

    @Test
    public void shouldCalculateOneDay_WhenDateFromAndDateToAreTheSame() {
        String dateFromAndTo = "2022-12-07";
        LocalDate dateFrom = LocalDate.parse(dateFromAndTo);
        LocalDate dateTo = LocalDate.parse(dateFromAndTo);

        assertEquals(1L, RentTimeCalculator.calculateNumberOfDaysForRent(dateFrom, dateTo));
    }

    @Test
    public void shouldCalculateThreeDays_WhenDateFromIsTwoDaysAfterDateTo() {
        LocalDate dateFrom = LocalDate.parse("2022-12-07");
        LocalDate dateTo = LocalDate.parse("2022-12-09");

        assertEquals(3L, RentTimeCalculator.calculateNumberOfDaysForRent(dateFrom, dateTo));
    }
}