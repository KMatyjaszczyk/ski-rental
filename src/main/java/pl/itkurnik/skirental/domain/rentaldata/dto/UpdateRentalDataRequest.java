package pl.itkurnik.skirental.domain.rentaldata.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateRentalDataRequest {
    private String companyName;
    private String taxId;
    private String regon;
    private String emailAddress;
    private String city;
    private String postCode;
    private String street;
    private String houseNumber;
    private String description;
}
