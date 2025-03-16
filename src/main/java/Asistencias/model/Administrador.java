package Asistencias.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Usuario {

    private String area;

    public Administrador() {}

    public Administrador(Long idUsuario,Long idUsuUni, String nombre, String email, String username, String password, String avatar, String area, String facultad) {
        super(idUsuario,idUsuUni, nombre, email, username, password, avatar,facultad);
        this.area = area;
    }

    // Getters y Setters
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
}
