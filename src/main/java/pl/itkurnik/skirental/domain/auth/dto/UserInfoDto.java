package pl.itkurnik.skirental.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserInfoDto {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Boolean isRegistered;
    private Set<String> roles;
}
