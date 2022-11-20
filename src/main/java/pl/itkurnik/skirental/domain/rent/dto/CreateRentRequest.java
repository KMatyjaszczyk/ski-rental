package pl.itkurnik.skirental.domain.rent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateRentRequest {
    private LocalDate plannedReturnDate;
    private Integer clientId;
    private Integer clientDocumentTypeId;
    private String clientDocumentNumber;
    private Integer employeeId;
    private String description;
    private List<Integer> rentItemsIds;
}
