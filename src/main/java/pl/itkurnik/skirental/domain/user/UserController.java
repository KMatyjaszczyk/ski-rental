package pl.itkurnik.skirental.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.itkurnik.skirental.domain.user.dto.CreateUnregisteredUserRequest;
import pl.itkurnik.skirental.domain.user.dto.UpdateUserRequest;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUnregisteredUser(@RequestBody CreateUnregisteredUserRequest request) {
        log.info("Creating unregistered user {} {}", request.getName(), request.getSurname());
        userService.createUnregisteredUser(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        log.info("Deleting user with ID {}", id);
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateUserRequest request) {
        log.info("Deleting user with ID {}", request.getId());
        userService.update(request);
        return ResponseEntity.ok().build();
    }
}
