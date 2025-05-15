package Asistencias.dto;

import java.util.Date;

import Asistencias.model.Administrativo;
import Asistencias.model.Estudiante;
import Asistencias.model.Profesor;
import Asistencias.model.Usuario;

public class UsuarioDTO {

    private Long idUsuario;
    private Long idUsuUni;
    private Integer cedula;
    private String nombre;
    private String email;
    private Integer facultadId;
    private String carrera; // Propiedad específica de Estudiante
    private String especialidad; // Propiedad específica de Profesor
    private String area; // Propiedad específica de Administrativo
    private Date fechaNacimiento; 
    private Boolean activo; 
    private String tipo;
    private String uid;

    // Constructor sin argumentos
    public UsuarioDTO() {}

    public UsuarioDTO(Long idUsuario, Long idUsuUni, Integer cedula, String nombre, String email, Integer facultadId,
                      String carrera, String especialidad, String area, Date fechaNacimiento, Boolean activo, String tipo, String uid) {
        this.idUsuario = idUsuario;
        this.idUsuUni = idUsuUni;
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.facultadId = facultadId;
        this.carrera = carrera;
        this.especialidad = especialidad;
        this.area = area;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
        this.tipo = tipo;
        this.uid = uid;
    }

    public UsuarioDTO(Usuario usuario) {
        this.idUsuario = usuario.getUsuarioId();
        this.idUsuUni = usuario.getIdUsuUni();
        this.cedula = usuario.getCedula();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.facultadId = usuario.getFacultadId();
        this.carrera = usuario instanceof Estudiante ? ((Estudiante) usuario).getCarrera() : null;
        this.especialidad = usuario instanceof Profesor ? ((Profesor) usuario).getEspecialidad() : null;
        this.area = usuario instanceof Administrativo ? ((Administrativo) usuario).getArea() : null;
        this.fechaNacimiento = usuario.getFechaNacimiento();
        this.activo = usuario.getActivo();
        this.tipo = usuario.getTipo();
        this.uid = usuario.getUid();
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public Integer getFacultadId() {
        return facultadId;
    }

    public void setFacultadId(Integer facultadId) {
        this.facultadId = facultadId;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
