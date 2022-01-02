package ro.unibuc.fmi.dietapp.backend.exception;

import javax.naming.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {
    public JwtTokenMissingException(String message) {
        super(message);
    }
}
