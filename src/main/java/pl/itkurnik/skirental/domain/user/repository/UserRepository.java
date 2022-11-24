package pl.itkurnik.skirental.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.role.Role;
import pl.itkurnik.skirental.domain.user.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    List<User> findAllByRolesIn(Set<Role> roles);
}