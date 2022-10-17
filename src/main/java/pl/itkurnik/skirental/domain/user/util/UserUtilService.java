package pl.itkurnik.skirental.domain.user.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.itkurnik.skirental.domain.user.User;
import pl.itkurnik.skirental.domain.user.service.UserService;
import pl.itkurnik.skirental.security.util.JwtUtil;
import pl.itkurnik.skirental.security.util.MyUserDetailsService;

@Service
@RequiredArgsConstructor
public class UserUtilService {
    private final JwtUtil jwtUtil;
    private final MyUserDetailsService userDetailsService;
    private final UserService userService;

    public Boolean validateToken(String token) {
        try {
            String username = jwtUtil.extractUsername(token);
            UserDetails user = userDetailsService.loadUserByUsername(username);
            return jwtUtil.validateToken(token, user);
        } catch (Exception e) {
            return false;
        }
    }

    public User getByToken(String token) {
        String username = jwtUtil.extractUsername(token);
        return userService.findByEmail(username);
    }
}
