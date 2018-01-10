package guru.springframework.spring5recipeapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NumberValueFormatException extends RuntimeException {
    public NumberValueFormatException() {
    }

    public NumberValueFormatException(String message) {
        super(message);
    }

    public NumberValueFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberValueFormatException(Throwable cause) {
        super(cause);
    }

    public NumberValueFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
