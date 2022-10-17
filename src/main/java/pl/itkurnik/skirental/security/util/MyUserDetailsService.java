package pl.itkurnik.skirental.security.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.user.User;
import pl.itkurnik.skirental.domain.user.service.UserRegisterService;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRegisterService userRegisterService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByEmail = userRegisterService.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Set<MyGrantedAuthority> authorities = userByEmail.getRoles().stream().map(RoleToAuthorityMapper::map).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(userByEmail.getEmail(), userByEmail.getPassword(), authorities);
    }
}
