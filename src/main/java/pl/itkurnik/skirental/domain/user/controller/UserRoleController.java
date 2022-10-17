package pl.itkurnik.skirental.domain.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.itkurnik.skirental.api.Constants;
import pl.itkurnik.skirental.domain.user.exception.UserNotFoundException;
import pl.itkurnik.skirental.domain.user.service.UserRoleService;

@RestController
@RequestMapping("/api/user_role")
@RequiredArgsConstructor
@Slf4j
public class UserRoleController {
    private final UserRoleService userRoleService;

    @PutMapping("/client")
    public ResponseEntity<Void> makeUserAsClient(@RequestParam Integer id) {
        try {
            log.info("Making user with id {} as client", id);
            userRoleService.makeUserAsClient(id);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PutMapping("/employee")
    public ResponseEntity<Void> makeUserAsEmployee(@RequestParam Integer id) {
        try {
            log.info("Making user with id {} as employee", id);
            userRoleService.makeUserAsEmployee(id);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }

    @PutMapping("/admin")
    public ResponseEntity<Void> makeUserAsAdmin(@RequestParam Integer id) {
        try {
            log.info("Making user with id {} as employee", id);
            userRoleService.makeUserAsAdmin(id);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            log.info(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.UNEXPECTED_ERROR_MESSAGE, e);
        }
    }
}
