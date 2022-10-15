package pl.itkurnik.skirental.domain.user;

import pl.itkurnik.skirental.domain.auth.dto.UserInfoDto;
import pl.itkurnik.skirental.domain.role.Role;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserInfoDto mapToInfoDto(User user) {
        UserInfoDto userInfo = new UserInfoDto();

        userInfo.setName(user.getName());
        userInfo.setSurname(user.getSurname());
        userInfo.setEmail(user.getEmail());
        userInfo.setPhoneNumber(user.getPhoneNumber());
        userInfo.setIsRegistered(user.getIsRegistered());
        userInfo.setRoles(user.getRoles().stream()
                .map(Role::getName).collect(Collectors.toSet()));

        return userInfo;
    }
}
