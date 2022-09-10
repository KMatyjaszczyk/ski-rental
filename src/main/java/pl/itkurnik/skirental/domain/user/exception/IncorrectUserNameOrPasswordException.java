package pl.itkurnik.skirental.domain.user.exception;

public class IncorrectUserNameOrPasswordException extends RuntimeException {

    public IncorrectUserNameOrPasswordException() {
        super("Incorrect username or password");
    }
}
