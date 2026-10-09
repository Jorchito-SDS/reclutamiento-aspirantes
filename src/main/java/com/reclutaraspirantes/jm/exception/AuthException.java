package main.java.com.reclutaraspirantes.jm.exception;

public class AuthException extends RuntimeException {
    public AuthException(String mensaje) {
        super(mensaje);
    }

    public AuthException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
