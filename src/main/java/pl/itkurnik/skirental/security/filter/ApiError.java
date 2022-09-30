package pl.itkurnik.skirental.security.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ApiError {
    private HttpStatus status;
    private String message;
    private OffsetDateTime timestamp;
}
