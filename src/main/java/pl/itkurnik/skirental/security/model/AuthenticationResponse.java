package pl.itkurnik.skirental.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {
    private String jwt;
    private Set<String> roles;
}
