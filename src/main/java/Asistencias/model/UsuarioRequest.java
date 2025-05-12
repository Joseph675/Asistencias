package Asistencias.model;

import Asistencias.dto.UsuarioDTO;
public class UsuarioRequest {
    private UsuarioDTO usuarioDTO;
    private String password;

    // Getters y setters
    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}