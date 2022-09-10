package pl.itkurnik.skirental.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateRegisteredUserRequest {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
}