package pl.itkurnik.skirental.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.role.RoleService;
import pl.itkurnik.skirental.domain.user.User;
import pl.itkurnik.skirental.domain.user.dto.CreateRegisteredUserRequest;
import pl.itkurnik.skirental.domain.user.repository.UserRepository;
import pl.itkurnik.skirental.domain.user.validation.CreateRegisteredUserValidator;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRegisterService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final CreateRegisteredUserValidator validator;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void processUserRegistration(CreateRegisteredUserRequest request) {
        validator.validateFields(request);
        validator.validateIfEmailIsAlreadyTaken(request.getEmail());
        validator.validateIfUserWithPhoneNumberIsAlreadyRegistered(request.getPhoneNumber());

        registerUser(request);
    }

    private void registerUser(CreateRegisteredUserRequest request) {
        Optional<User> userByPhoneNumber = userRepository.findByPhoneNumber(
                request.getPhoneNumber());
        if (userByPhoneNumber.isPresent()) {
            makeUserRegistered(userByPhoneNumber.get(), request);
            return;
        }

        createRegisteredUser(request);
    }

    private void makeUserRegistered(User userByPhoneNumber, CreateRegisteredUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        userByPhoneNumber.setName(request.getName());
        userByPhoneNumber.setSurname(request.getSurname());
        userByPhoneNumber.setEmail(request.getEmail());
        userByPhoneNumber.setPassword(encodedPassword);
        userByPhoneNumber.setIsRegistered(Boolean.TRUE);

        userRepository.save(userByPhoneNumber);

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
        user.getRoles().add(roleService.getClientRole());

        userRepository.save(user);
    }
}
