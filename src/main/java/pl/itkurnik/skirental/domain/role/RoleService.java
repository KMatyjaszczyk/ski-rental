package pl.itkurnik.skirental.domain.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.role.exception.RoleNotFoundException;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getClientRole() {
        return findByName(Roles.CLIENT.getName());
    }

    public Role getAdminRole() {
        return findByName(Roles.ADMIN.getName());
    }

    public Role getEmployeeRole() {
        return findByName(Roles.EMPLOYEE.getName());
    }

    private Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException(name));
    }
}
