package pl.itkurnik.skirental.util;

import java.time.Duration;
import java.time.LocalDate;

public class RentTimeCalculator {
    private static final Long DAY_WHEN_RENT_WAS_STARTED = 1L;

    public static Long calculateNumberOfDaysForRent(LocalDate dateFrom, LocalDate dateTo) {
        long daysBetweenDates = Duration.between(dateFrom.atStartOfDay(), dateTo.atStartOfDay()).toDays();

        return DAY_WHEN_RENT_WAS_STARTED + daysBetweenDates;
    }
}
