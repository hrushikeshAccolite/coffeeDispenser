package com.dispenser.coffee.exception;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.io.Serial;

@ResponseStatus
public class DispenserException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;

    public DispenserException(String message) {
            super(message);
    }

    public DispenserException(String message, Throwable cause) {
        super(message, cause);
    }
}
