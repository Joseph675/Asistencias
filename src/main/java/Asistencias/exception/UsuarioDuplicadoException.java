package Asistencias.exception;

public class UsuarioDuplicadoException extends RuntimeException {
    public UsuarioDuplicadoException(String message) {
        super(message);
    }
}