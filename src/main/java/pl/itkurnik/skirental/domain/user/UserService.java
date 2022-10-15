package pl.itkurnik.skirental.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.role.RoleService;
import pl.itkurnik.skirental.domain.user.dto.CreateUnregisteredUserRequest;
import pl.itkurnik.skirental.domain.user.dto.UpdateUserRequest;
import pl.itkurnik.skirental.domain.user.exception.UserNotFoundException;
import pl.itkurnik.skirental.domain.user.validation.CreateUnregisteredUserValidator;
import pl.itkurnik.skirental.domain.user.validation.UpdateUserValidator;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CreateUnregisteredUserValidator createUnregisteredUserValidator;
    private final UpdateUserValidator updateUserValidator;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public void createUnregisteredUser(CreateUnregisteredUserRequest request) {
        createUnregisteredUserValidator.validateFields(request);
        createUnregisteredUserValidator.validateIfPhoneNumberIsAlreadyTaken(request.getPhoneNumber());

        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setIsRegistered(Boolean.FALSE);
        user.getRoles().add(roleService.getClientRole());

        userRepository.save(user);
    }

    public void deleteById(Integer id) {
        try {
            userRepository.deleteById(id); // TODO KM preferred will be setting flag 'deleted'
        } catch (EmptyResultDataAccessException ignored) {
            log.info("User with ID {} already deleted", id);
        }
    }

    public void update(UpdateUserRequest request) {
        updateUserValidator.validateFields(request);

        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new UserNotFoundException(request.getId()));
        updateProperFields(request, user);

        userRepository.save(user);
    }

    private void updateProperFields(UpdateUserRequest request, User user) { // TODO KM check if user is registered - if not, email cannot be set
        if (!Objects.isNull(request.getName())) {
            user.setName(request.getName());
        }

        if (!Objects.isNull(request.getSurname())) {
            user.setSurname(request.getSurname());
        }

        if (!Objects.isNull(request.getPhoneNumber())) {
            user.setPhoneNumber(request.getPhoneNumber());
        }

        if (!Objects.isNull(request.getEmail())) {
            user.setEmail(request.getEmail());
        }
    }
}
