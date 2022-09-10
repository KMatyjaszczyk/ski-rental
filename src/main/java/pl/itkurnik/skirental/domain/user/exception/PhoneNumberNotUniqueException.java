package pl.itkurnik.skirental.domain.user.exception;

public class PhoneNumberNotUniqueException extends RuntimeException {

    public PhoneNumberNotUniqueException(String phoneNumber) {
        super(String.format("Phone number %s is not unique", phoneNumber));
    }
}
