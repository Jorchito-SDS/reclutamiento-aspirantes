package main.java.com.reclutaraspirantes.jm.exception;

public class VistaException extends RuntimeException {
    public VistaException(String mensaje) {
        super(mensaje);
    }

    public VistaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
