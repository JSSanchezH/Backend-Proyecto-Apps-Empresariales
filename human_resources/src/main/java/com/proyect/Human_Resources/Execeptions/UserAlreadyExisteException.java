package com.proyect.Human_Resources.Execeptions;

public class UserAlreadyExisteException extends RuntimeException {

    public UserAlreadyExisteException(String message) {
        super(message);
    }

    public UserAlreadyExisteException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExisteException(Throwable cause) {
        super(cause);
    }
    
}
