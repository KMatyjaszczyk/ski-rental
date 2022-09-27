package pl.itkurnik.skirental.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleDefault(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setMessage(ex.getMessage());
        apiError.setTimestamp(OffsetDateTime.now(ZoneId.systemDefault()));

        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex) { // TODO KM it doesnt work
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setMessage(ex.getMessage());
        apiError.setTimestamp(OffsetDateTime.now(ZoneId.systemDefault()));

        return new ResponseEntity<>(apiError, status);
    }
}
