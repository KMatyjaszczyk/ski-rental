package pl.itkurnik.skirental.domain.rent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.itkurnik.skirental.domain.auth.dto.UserInfoDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RentInfoDto {
    private Integer id;
    private LocalTime rentDate;
    private LocalDate plannedReturnDate;
    private LocalTime endDate;
    private UserInfoDto client;
    private RentStatusInfoDto rentStatus;
    private ClientDocumentTypeInfoDto clientDocumentType;
    private String clientDocumentNumber;
    private UserInfoDto employee;
    private String description;
    private List<RentItemInfoDto> rentItems;
}
