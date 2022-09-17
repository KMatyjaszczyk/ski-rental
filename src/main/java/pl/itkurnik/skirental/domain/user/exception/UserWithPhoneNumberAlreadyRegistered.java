package pl.itkurnik.skirental.domain.user.exception;

public class UserWithPhoneNumberAlreadyRegistered extends RuntimeException {

    public UserWithPhoneNumberAlreadyRegistered(String phoneNumber) {
        super(String.format("User with phone number %s is already registered", phoneNumber));
    }
}
