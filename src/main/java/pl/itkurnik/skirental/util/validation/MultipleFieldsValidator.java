package pl.itkurnik.skirental.util.validation;

import pl.itkurnik.skirental.util.ErrorMessagesBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class MultipleFieldsValidator<T> {

    public void validateFields(T validatedObject) {
        List<String> errorMessages = new ArrayList<>();

        processFieldsValidation(validatedObject, errorMessages);

        verifyValidationResult(errorMessages);
    }

    protected abstract void processFieldsValidation(T validatedObject, List<String> errorMessages);

    private void verifyValidationResult(List<String> errorMessages) {
        if (!errorMessages.isEmpty()) {
            String combinedMessage = ErrorMessagesBuilder.ofMessages(errorMessages);
            Class<? extends RuntimeException> exceptionType = getValidationExceptionType();

            throwException(combinedMessage, exceptionType);
        }
    }

    protected abstract Class<? extends ValidationException> getValidationExceptionType();

    private void throwException(String combinedMessage, Class<? extends RuntimeException> exceptionType) {
        try {
            throw exceptionType.getConstructor(String.class).newInstance(combinedMessage);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
