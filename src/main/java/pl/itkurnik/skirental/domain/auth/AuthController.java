package pl.itkurnik.skirental.domain.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.user.UserLoginService;
import pl.itkurnik.skirental.domain.user.UserRegisterService;
import pl.itkurnik.skirental.domain.user.dto.CreateRegisteredUserRequest;
import pl.itkurnik.skirental.domain.user.dto.TokenValidationResponse;
import pl.itkurnik.skirental.domain.user.exception.CreateUserValidationException;
import pl.itkurnik.skirental.domain.user.exception.EmailAlreadyTakenException;
import pl.itkurnik.skirental.domain.user.exception.IncorrectUserNameOrPasswordException;
import pl.itkurnik.skirental.domain.user.exception.UserWithPhoneNumberAlreadyRegistered;
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
            AuthenticationResponse response = userLoginService.loginAndGetJwt(request);
            log.info("User {} successfully logged in", request.getUsername());
            return ResponseEntity.ok(response);
        } catch (IncorrectUserNameOrPasswordException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody CreateRegisteredUserRequest request) {
        log.info("Registering user {} {}", request.getName(), request.getSurname());

        try {
            userRegisterService.processUserRegistration(request);
            log.info("User {} {} successfully registered", request.getName(), request.getSurname());
            return ResponseEntity.ok().build();
        } catch (CreateUserValidationException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (EmailAlreadyTakenException | UserWithPhoneNumberAlreadyRegistered e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenValidationResponse> validateToken(@RequestBody String token) {
        log.info("Validating token {}", token);

        try {
            TokenValidationResponse response = new TokenValidationResponse(userDetailsService.validateToken(token));
            log.info("Token {} successfully validated", token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    // TODO KM change password endpoint should be here
}
