package Asistencias.dto;

public class UsuarioDTO {
    
    private Long idUsuario;
    private String nombre;
    private String email;
    private String username;
    private String avatar;
    
    // Constructor sin argumentos
    public UsuarioDTO() {
    }
    
    // Constructor con parámetros
    public UsuarioDTO(Long idUsuario, String nombre, String email, String username, String avatar) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.username = username;
        this.avatar = avatar;
    }
    
    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
