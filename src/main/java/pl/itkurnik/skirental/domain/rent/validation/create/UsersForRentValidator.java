package pl.itkurnik.skirental.domain.rent.validation.create;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.itkurnik.skirental.domain.rent.dto.CreateRentRequest;
import pl.itkurnik.skirental.domain.role.Role;
import pl.itkurnik.skirental.domain.role.RoleService;
import pl.itkurnik.skirental.domain.user.User;
import pl.itkurnik.skirental.domain.user.exception.UserNotFoundException;
import pl.itkurnik.skirental.domain.user.service.UserService;

@Component
@RequiredArgsConstructor
class UsersForRentValidator {
    private final UserService userService;
    private final RoleService roleService;

    public void validate(CreateRentRequest request) {
        validateIfUsersExist(request);
        validateIfUsersHaveProperRoles(request);
    }

    private void validateIfUsersExist(CreateRentRequest request) {
        boolean clientDoesNotExist = !userService.existsById(request.getClientId());
        if (clientDoesNotExist) {
            throw new UserNotFoundException(request.getClientId());
        }

        boolean employeeDoesNotExist = !userService.existsById(request.getEmployeeId());
        if (employeeDoesNotExist) {
            throw new UserNotFoundException(request.getEmployeeId());
        }
    }

    private void validateIfUsersHaveProperRoles(CreateRentRequest request) {
        User client = userService.findById(request.getClientId());
        boolean clientDoesNotHaveClientRole = isRoleNotAssignedToUser(client, roleService.getClientRole());

        if (clientDoesNotHaveClientRole) {
           throw new IllegalStateException(String.format("User with id %d does not have client role", client.getId()));
        }

        User employee = userService.findById(request.getEmployeeId());
        boolean employeeDoesNotHaveEmployeeRole = isRoleNotAssignedToUser(employee, roleService.getEmployeeRole());

        if (employeeDoesNotHaveEmployeeRole) {
            throw new IllegalStateException(String.format("User with id %d does not have employee role", employee.getId()));
        }

    }

    private boolean isRoleNotAssignedToUser(User user, Role roleToVerify) {
        return user.getRoles().stream()
                .noneMatch(role -> role.getId().equals(roleToVerify.getId()));
    }
}
