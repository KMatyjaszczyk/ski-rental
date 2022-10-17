package pl.itkurnik.skirental.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.itkurnik.skirental.domain.role.RoleRepository;
import pl.itkurnik.skirental.domain.user.repository.UserRepository;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    @GetMapping("/test2")
    public String test2() {
//        User user = new User();
//        user.setName("Jan");
//        user.setSurname("Kowalski");
//        user.setPhoneNumber("+48111222333");
//        user.setEmail("jan.kowalsk@example.com");
//        user.setPassword("123456");
//        user.setIsRegistered(true);
//
//        userRepository.save(user);
//
//        Role role = new Role();
//        role.setName("employee");
//
//        roleRepository.save(role);

//        Role employeeRole = roleRepository.findByName("EMPLOYEE").orElseThrow(() -> new RuntimeException("Role not found"));
//        User user = userRepository.findById(1).orElseThrow(() -> new RuntimeException("User not found"));
//        user.getRoles().add(employeeRole);
//        userRepository.save(user);


        return "done";
    }
}
