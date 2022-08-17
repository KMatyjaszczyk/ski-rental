package pl.itkurnik.skirental.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.user.User;
import pl.itkurnik.skirental.domain.user.UserService;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByEmail = userService.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Set<MyGrantedAuthority> authorities = userByEmail.getRoles().stream().map(RoleToAuthorityMapper::map).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(userByEmail.getEmail(), userByEmail.getPassword(), authorities);
    }

    public Boolean validateToken(String token) {
        try {
            String username = jwtUtil.extractUsername(token);
            UserDetails user = loadUserByUsername(username);
            return jwtUtil.validateToken(token, user);
        } catch (Exception e) {
            return false;
        }
    }
}
