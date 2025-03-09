package Asistencias.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, 
    include = JsonTypeInfo.As.PROPERTY, 
    property = "tipoUsuario"  // Esta propiedad deberá venir en el JSON
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Estudiante.class, name = "ESTUDIANTE"),
    @JsonSubTypes.Type(value = Profesor.class, name = "PROFESOR"),
    @JsonSubTypes.Type(value = Administrador.class, name = "ADMINISTRADOR")
})
 
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@Entity
@Table(name = "usuario")
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private int idUsuUni;
    private String nombre;
    private String email;
    private String username;  
    private String password;
    private String avatar;
    private String facultad;


    // Constructor sin argumentos
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(int idUsuUni, String nombre, String email, String username, String password, String avatar,String facultad) {
        this.idUsuUni = idUsuUni;
        this.nombre = nombre;
        this.email = email;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.facultad = facultad;
    }

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public int getIdUsuUni() {
        return idUsuUni;
    }

    public void setIdUsuUni(int idUsuUni) {
        this.idUsuUni = idUsuUni;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    
}
