package pl.itkurnik.skirental.domain.user;

import lombok.RequiredArgsConstructor;
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
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CreateUnregisteredUserValidator createUnregisteredUserValidator;
    private final UpdateUserValidator updateUserValidator;

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
        userRepository.deleteById(id); // TODO KM preferred will be setting flag 'deleted'
    }

    public void update(UpdateUserRequest request) {
        updateUserValidator.validateFields(request);

        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new UserNotFoundException(request.getId()));
        updateProperFields(request, user);

        userRepository.save(user);
    }

    private void updateProperFields(UpdateUserRequest request, User user) {
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
