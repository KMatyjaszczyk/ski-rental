package pl.itkurnik.skirental.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.user.exception.IncorrectUserNameOrPasswordException;
import pl.itkurnik.skirental.security.model.AuthenticationRequest;
import pl.itkurnik.skirental.security.util.JwtUtil;
import pl.itkurnik.skirental.security.util.MyUserDetailsService;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public String loginAndGetJwt(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new IncorrectUserNameOrPasswordException();
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        return jwtUtil.generateToken(userDetails);
    }
}
