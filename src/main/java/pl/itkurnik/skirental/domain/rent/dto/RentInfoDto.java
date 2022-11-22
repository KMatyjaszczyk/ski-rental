package pl.itkurnik.skirental.domain.rent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.itkurnik.skirental.domain.auth.dto.UserInfoDto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RentInfoDto {
    private Integer id;
    private Instant rentDate;
    private LocalDate plannedReturnDate;
    private Instant endDate;
    private UserInfoDto client;
    private RentStatusInfoDto rentStatus;
    private ClientDocumentTypeInfoDto clientDocumentType;
    private String clientDocumentNumber;
    private UserInfoDto employee;
    private String description;
    private List<RentItemInfoDto> rentItems;
}
