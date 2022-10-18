package pl.itkurnik.skirental.domain.user.exception;

public class UserWithPhoneNumberAlreadyRegisteredException extends RuntimeException {

    public UserWithPhoneNumberAlreadyRegisteredException(String phoneNumber) {
        super(String.format("User with phone number %s is already registered", phoneNumber));
    }
}
