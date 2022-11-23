package pl.itkurnik.skirental.domain.user.exception;

import pl.itkurnik.skirental.util.error.ObjectNotFoundException;

public class UserNotFoundException extends ObjectNotFoundException {

    public UserNotFoundException(Integer id) {
        super(String.format("User with id %s not found", id));
    }

    public UserNotFoundException(String email) {
        super(String.format("User with email %s not found", email));
    }
}
