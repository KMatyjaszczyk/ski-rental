package pl.itkurnik.skirental.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.role.Role;
import pl.itkurnik.skirental.domain.role.RoleService;
import pl.itkurnik.skirental.domain.user.User;
import pl.itkurnik.skirental.domain.user.dto.CreateUnregisteredUserRequest;
import pl.itkurnik.skirental.domain.user.dto.UpdateUserRequest;
import pl.itkurnik.skirental.domain.user.exception.UserNotFoundException;
import pl.itkurnik.skirental.domain.user.repository.UserRepository;
import pl.itkurnik.skirental.domain.user.validation.CreateUnregisteredUserValidator;
import pl.itkurnik.skirental.domain.user.validation.UpdateUserValidator;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CreateUnregisteredUserValidator createUnregisteredUserValidator;
    private final UpdateUserValidator updateUserValidator;

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllClients() {
        Set<Role> clientRoleSingleton = Collections.singleton(roleService.getClientRole());

        return userRepository.findAllByRolesIn(clientRoleSingleton);
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
            userRepository.deleteById(id);
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

    private void updateProperFields(UpdateUserRequest request, User user) {
        if (!StringUtils.equals(request.getName(), user.getName())) {
            user.setName(request.getName());
        }

        if (!StringUtils.equals(request.getSurname(), user.getSurname())) {
            user.setSurname(request.getSurname());
        }

        if (!StringUtils.equals(request.getPhoneNumber(), user.getPhoneNumber())) {
            user.setPhoneNumber(request.getPhoneNumber());
        }

        updateEmailIfPossible(request, user);
    }

    private void updateEmailIfPossible(UpdateUserRequest request, User user) {
        boolean userIsNotRegistered = !user.getIsRegistered();
        if (userIsNotRegistered) {
            return;
        }

        if (!StringUtils.equals(request.getEmail(), user.getEmail())) {
            user.setEmail(request.getEmail());
        }
    }

    public Boolean existsById(Integer id) {
        return userRepository.existsById(id);
    }
}
