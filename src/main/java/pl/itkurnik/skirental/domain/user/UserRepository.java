package pl.itkurnik.skirental.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.itkurnik.skirental.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}