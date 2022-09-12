package pl.itkurnik.skirental.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenValidationResponse {
    private Boolean response;
}