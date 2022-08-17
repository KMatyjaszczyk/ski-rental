package pl.itkurnik.skirental.security;

import pl.itkurnik.skirental.domain.role.Role;

public class RoleToAuthorityMapper {

    private RoleToAuthorityMapper() {}

    public static MyGrantedAuthority map(Role role) {
        return new MyGrantedAuthority(role.getName());
    }
}
