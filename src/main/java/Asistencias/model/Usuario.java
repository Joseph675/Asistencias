package Asistencias.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "tipoUsuario" // Esta propiedad deberá venir en el JSON
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Estudiante.class, name = "alumno"),
    @JsonSubTypes.Type(value = Profesor.class, name = "profesor"),
    @JsonSubTypes.Type(value = Administrativo.class, name = "administrativo"),
    @JsonSubTypes.Type(value = Administrador.class, name = "admin")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@Entity
@Table(name = "usuario")
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    @Column(nullable = false, unique = true)
    private Long idUsuUni;

    @Column(nullable = false, unique = true)
    private Integer cedula;

    @Column(nullable = false, length = 100)
    private String nombre;

   
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;

    @Column(nullable = false)
    private String facultad; 

    // Constructor sin argumentos
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(Long usuarioId, Long idUsuUni, Integer cedula, String nombre, String email, String passwordHash, Date fechaNacimiento, Boolean activo, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, String facultad) {
        this.usuarioId = usuarioId;
        this.idUsuUni = idUsuUni;
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.facultad = facultad;
    }

    // Getters y Setters
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getIdUsuUni() {
        return idUsuUni;
    }

    public void setIdUsuUni(Long idUsuUni) {
        this.idUsuUni = idUsuUni;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;   
    }
}
