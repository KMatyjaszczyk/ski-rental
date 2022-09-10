package pl.itkurnik.skirental.domain.user.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String email) {
        super(String.format("User %s already exists", email));
    }
}
