package Asistencias.dto;

public class UsuarioDTO {

    private Long idUsuario;
    private Long idUsuUni;
    private String nombre;
    private String email;
    private String facultad;
    private String carrera; // Propiedad específica de Estudiante
    private String especialidad; // Propiedad específica de Profesor
    private String area; // Propiedad específica de Administrativo
    private String tipoUsuario;

    // Constructor sin argumentos
    public UsuarioDTO() {}

    // Constructor con parámetros
    public UsuarioDTO(Long idUsuario, Long idUsuUni, String nombre, String email, String facultad, String carrera, String especialidad, String area, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.idUsuUni = idUsuUni;
        this.nombre = nombre;
        this.email = email;
        this.facultad = facultad;
        this.carrera = carrera;
        this.especialidad = especialidad;
        this.area = area;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters y Setters
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

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
