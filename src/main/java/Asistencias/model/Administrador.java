package Asistencias.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "administradores")
public class Administrador {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_administrador;

    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rol;
    private String avatar;

    // Constructor sin argumentos
    public Administrador() {
    }

    // Getters y setters
    public Long getId_administrador() {
        return id_administrador;
    }

    public void setId_administrador(Long id_administrador) {
        this.id_administrador = id_administrador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


  
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getrol() {
        return rol;
    }

    

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
