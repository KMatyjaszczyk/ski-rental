package pl.itkurnik.skirental.domain.user.exception;

public class PhoneNumberAlreadyTakenException extends RuntimeException {

    public PhoneNumberAlreadyTakenException(String phoneNumber) {
        super(String.format("User with phone number %s already exists", phoneNumber));
    }
}
