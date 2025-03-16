package Asistencias.service;

import Asistencias.exception.UsuarioDuplicadoException;
import Asistencias.model.Usuario;
import Asistencias.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    // Obtener un usuario por su ID
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Guardar o actualizar un usuario
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Eliminar un usuario por su ID
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Registrar un nuevo usuario con verificación de duplicados
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new UsuarioDuplicadoException("El email ya está registrado.");
        }
        if (usuarioRepository.findByIdUsuUni(usuario.getIdUsuUni()).isPresent()) {
            throw new UsuarioDuplicadoException("El ID de usuario universitario ya está registrado.");
        }
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new UsuarioDuplicadoException("El nombre de usuario ya está registrado.");
        }
        return usuarioRepository.save(usuario);
    }
}
