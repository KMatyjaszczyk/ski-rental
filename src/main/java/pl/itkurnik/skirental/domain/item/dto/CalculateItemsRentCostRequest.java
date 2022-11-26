package pl.itkurnik.skirental.domain.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CalculateItemsRentCostRequest {
    private List<Integer> itemsIds;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
