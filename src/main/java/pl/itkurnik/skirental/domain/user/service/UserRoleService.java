package pl.itkurnik.skirental.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.role.Role;
import pl.itkurnik.skirental.domain.role.RoleService;
import pl.itkurnik.skirental.domain.user.User;
import pl.itkurnik.skirental.domain.user.repository.UserRepository;
import pl.itkurnik.skirental.domain.user.exception.UserNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public void makeUserAsClient(Integer userId) {
        Set<Role> roleToSet = Collections.singleton(roleService.getClientRole());

        clearUserRolesAndSetNewOnes(userId, roleToSet);
    }

    public void makeUserAsEmployee(Integer userId) {
        Set<Role> roleToSet = Collections.singleton(roleService.getEmployeeRole());

        clearUserRolesAndSetNewOnes(userId, roleToSet);
    }

    public void makeUserAsAdmin(Integer userId) {
        Set<Role> rolesToSet = new HashSet<>();
        rolesToSet.add(roleService.getEmployeeRole());
        rolesToSet.add(roleService.getAdminRole());

        clearUserRolesAndSetNewOnes(userId, rolesToSet);
    }

    private void clearUserRolesAndSetNewOnes(Integer userId, Collection<Role> roles) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId)); // TODO KM check if user is registered, if not he should not have any role

        user.getRoles().clear();
        user.getRoles().addAll(roles);

        userRepository.save(user);
    }
}
