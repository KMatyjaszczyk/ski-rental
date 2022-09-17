package pl.itkurnik.skirental.domain.user.exception;

public class EmailAlreadyTakenException extends RuntimeException {

    public EmailAlreadyTakenException(String email) {
        super(String.format("User with email %s already exists", email));
    }
}
