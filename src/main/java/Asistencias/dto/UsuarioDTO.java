package Asistencias.dto;

public class UsuarioDTO {

    private Long idUsuario;
    private Long idUsuUni;
    private Integer cedula;
    private String nombre;
    private String email;
    private String facultadId;
    private String carrera; // Propiedad específica de Estudiante
    private String especialidad; // Propiedad específica de Profesor
    private String area; // Propiedad específica de Administrativo
    private String tipo;
    private String password;

    // Constructor sin argumentos
    public UsuarioDTO() {}

    // Constructor con parámetros
    public UsuarioDTO(Long idUsuario, Long idUsuUni, Integer cedula, String nombre, String email, String facultadId, String carrera, String especialidad, String area, String tipo, String password) {
        this.idUsuario = idUsuario;
        this.idUsuUni = idUsuUni;
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.facultadId = facultadId;
        this.carrera = carrera;
        this.especialidad = especialidad;
        this.area = area;
        this.tipo = tipo;
        this.password = password;
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

    public String getFacultad() {
        return facultadId;
    }

    public void setFacultad(String facultadId) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
