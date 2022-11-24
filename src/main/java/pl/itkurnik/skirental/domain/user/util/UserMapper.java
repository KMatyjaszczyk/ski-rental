package pl.itkurnik.skirental.domain.user.util;

import pl.itkurnik.skirental.domain.auth.dto.UserInfoDto;
import pl.itkurnik.skirental.domain.role.Role;
import pl.itkurnik.skirental.domain.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserInfoDto mapToInfoDto(User user) {
        UserInfoDto userInfo = new UserInfoDto();

        userInfo.setId(user.getId());
        userInfo.setName(user.getName());
        userInfo.setSurname(user.getSurname());
        userInfo.setEmail(user.getEmail());
        userInfo.setPhoneNumber(user.getPhoneNumber());
        userInfo.setIsRegistered(user.getIsRegistered());
        userInfo.setRoles(user.getRoles().stream()
                .map(Role::getName).collect(Collectors.toSet()));

        return userInfo;
    }

    public static List<UserInfoDto> mapAllToInfoDto(List<User> users) {
        return users.stream()
                .map(UserMapper::mapToInfoDto)
                .collect(Collectors.toList());
    }
}
