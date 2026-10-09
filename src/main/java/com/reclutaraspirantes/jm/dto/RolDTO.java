package main.java.com.reclutaraspirantes.jm.dto;

public record RolDTO(int id, String nombre) {
    @Override
    public String toString() {
        return nombre;
    }
}
