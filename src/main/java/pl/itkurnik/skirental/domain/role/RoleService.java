package pl.itkurnik.skirental.domain.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.role.exception.RoleNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getClientRole() {
        String clientRoleName = Roles.CLIENT.getName();

        return findByName(clientRoleName)
                .orElseThrow(() -> new RoleNotFoundException(clientRoleName));
    }

    private Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
