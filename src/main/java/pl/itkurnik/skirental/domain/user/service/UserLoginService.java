package pl.itkurnik.skirental.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.role.Role;
import pl.itkurnik.skirental.domain.user.User;
import pl.itkurnik.skirental.domain.user.exception.IncorrectUserNameOrPasswordException;
import pl.itkurnik.skirental.security.model.AuthenticationRequest;
import pl.itkurnik.skirental.security.model.AuthenticationResponse;
import pl.itkurnik.skirental.security.util.JwtUtil;
import pl.itkurnik.skirental.security.util.MyUserDetailsService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthenticationResponse loginAndGetJwt(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new IncorrectUserNameOrPasswordException();
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        User userByEmail = userService.findByEmail(userDetails.getUsername());
        Set<String> roleNames = userByEmail.getRoles().stream()
                .map(Role::getName).collect(Collectors.toSet());

        return new AuthenticationResponse(token, roleNames);
    }
}
