package pl.itkurnik.skirental.domain.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.itkurnik.skirental.domain.user.UserLoginService;
import pl.itkurnik.skirental.domain.user.UserRegisterService;
import pl.itkurnik.skirental.domain.user.dto.CreateRegisteredUserRequest;
import pl.itkurnik.skirental.domain.user.dto.TokenValidationResponse;
import pl.itkurnik.skirental.domain.user.exception.IncorrectUserNameOrPasswordException;
import pl.itkurnik.skirental.security.model.AuthenticationRequest;
import pl.itkurnik.skirental.security.model.AuthenticationResponse;
import pl.itkurnik.skirental.security.util.MyUserDetailsService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserRegisterService userRegisterService;
    private final UserLoginService userLoginService;
    private final MyUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        log.info("Logging in with username {}", request.getUsername());

        try {
            String token = userLoginService.loginAndGetJwt(request);
            return ResponseEntity.ok(new AuthenticationResponse(token));
        } catch (IncorrectUserNameOrPasswordException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // TODO KM API Exceptions handling
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody CreateRegisteredUserRequest request) {
        log.info("Registering user {} {}", request.getName(), request.getSurname());

        userRegisterService.processUserRegistration(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenValidationResponse> validateToken(@RequestBody String token) {
        log.info("Validating token {}", token);
        return ResponseEntity.ok(new TokenValidationResponse(userDetailsService.validateToken(token)));
    }
}
