package pl.itkurnik.skirental.domain.role.exception;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(Integer id) {
        super(String.format("Role with id %d not found", id));
    }

    public RoleNotFoundException(String name) {
        super(String.format("Role with name %s not found", name));
    }
}
