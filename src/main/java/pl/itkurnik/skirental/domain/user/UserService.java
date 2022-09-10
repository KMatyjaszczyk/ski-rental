package pl.itkurnik.skirental.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.role.Role;
import pl.itkurnik.skirental.domain.role.RoleService;
import pl.itkurnik.skirental.domain.role.Roles;
import pl.itkurnik.skirental.domain.role.exception.RoleNotFoundException;
import pl.itkurnik.skirental.domain.user.dto.CreateRegisteredUserRequest;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final CreateRegisteredUserValidator validator;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void registerUser(CreateRegisteredUserRequest request) {
        validator.validateRequestData(request);
        validator.validateIfUserExists(request.getEmail());
        validator.validatePhoneNumberUniqueness(request.getPhoneNumber());

        createRegisteredUser(request);
    }

    private void createRegisteredUser(CreateRegisteredUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);
        user.setIsRegistered(Boolean.TRUE);
        user.getRoles().add(getClientRole());

        userRepository.save(user);
    }

    private Role getClientRole() {
        String clientRoleName = Roles.CLIENT.getName();

        return roleService.findByName(clientRoleName)
                .orElseThrow(() -> new RoleNotFoundException(clientRoleName));
    }
}
