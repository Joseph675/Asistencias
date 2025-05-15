package Asistencias.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@Entity
@Table(name = "usuarios")
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

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime fechaActualizacion;

    @Column()
    private Integer facultadId; 
   
    @Column(nullable = false, unique = true)
    private String uid;

    @Column(name = "tipo", insertable = false, updatable = false)
    private String tipo; // Gestionado por JPA, pero accesible desde el código
    
    // Constructor sin argumentos
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(Long usuarioId, Long idUsuUni, Integer cedula, String nombre, String email, String passwordHash, Date fechaNacimiento, Boolean activo, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, Integer facultadId, String uid) {
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
        this.facultadId = facultadId;
        this.uid = uid;
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

    public Integer getFacultadId() {
        return facultadId;
    }


    public void setFacultadId(Integer facultadId) {
        this.facultadId = facultadId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    
}
