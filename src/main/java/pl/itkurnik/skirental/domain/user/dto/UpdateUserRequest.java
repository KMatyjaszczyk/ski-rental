package pl.itkurnik.skirental.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private Integer id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
}
