package pl.itkurnik.skirental.domain.rent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReturnRentItemsRequest {
    private Integer rentId;
    private List<Integer> itemsIds;
}
