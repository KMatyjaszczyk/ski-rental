package pl.itkurnik.skirental.domain.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.auth.dto.UserInfoDto;
import pl.itkurnik.skirental.domain.user.User;
import pl.itkurnik.skirental.domain.user.dto.CreateUnregisteredUserRequest;
import pl.itkurnik.skirental.domain.user.dto.UpdateUserRequest;
import pl.itkurnik.skirental.domain.user.exception.CreateUserValidationException;
import pl.itkurnik.skirental.domain.user.exception.PhoneNumberAlreadyTakenException;
import pl.itkurnik.skirental.domain.user.exception.UpdateUserValidationException;
import pl.itkurnik.skirental.domain.user.exception.UserNotFoundException;
import pl.itkurnik.skirental.domain.user.service.UserService;
import pl.itkurnik.skirental.domain.user.util.UserMapper;

import java.util.List;

import static pl.itkurnik.skirental.api.Constants.CROSS_ORIGIN_MAX_AGE;
import static pl.itkurnik.skirental.api.Constants.LOCALHOST_FRONTEND_APP_URL;

@CrossOrigin(origins = LOCALHOST_FRONTEND_APP_URL, maxAge = CROSS_ORIGIN_MAX_AGE)
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserInfoDto>> findAll() {
        try {
            log.info("Receiving all users");
            List<User> users = userService.findAll();
            log.info("All users received successfully");
            return ResponseEntity.ok(UserMapper.mapAllToInfoDto(users));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/client")
    public ResponseEntity<List<UserInfoDto>> findAllClients() {
        try {
            log.info("Receiving all client users");
            List<User> clients = userService.findAllClients();
            log.info("All client users received successfully");
            return ResponseEntity.ok(UserMapper.mapAllToInfoDto(clients));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PostMapping
    public ResponseEntity<Void> createUnregisteredUser(@RequestBody CreateUnregisteredUserRequest request) {
        log.info("Creating unregistered user {} {}", request.getName(), request.getSurname());

        try {
            userService.createUnregisteredUser(request);
            log.info("Unregistered user {} {} successfully created", request.getName(), request.getSurname());
            return ResponseEntity.ok().build();
        } catch (CreateUserValidationException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (PhoneNumberAlreadyTakenException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        log.info("Deleting user with ID {}", id);

        try {
            userService.deleteById(id);
            log.info("User with ID {} successfully deleted", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateUserRequest request) {
        log.info("Updating user with ID {}", request.getId());

        try {
            userService.update(request);
            log.info("User with ID {} successfully updated", request.getId());
            return ResponseEntity.ok().build();
        } catch (UpdateUserValidationException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (UserNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
