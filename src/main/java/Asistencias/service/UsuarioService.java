package Asistencias.service;

import Asistencias.exception.UsuarioDuplicadoException;
import Asistencias.model.Usuario;
import Asistencias.model.Estudiante;
import Asistencias.model.Profesor;
import Asistencias.model.Administrativo;
import Asistencias.dto.UsuarioDTO;
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

    /**
     * Obtener todos los usuarios.
     * @return Lista de usuarios.
     */
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Obtener un usuario por su ID.
     * @param id ID del usuario.
     * @return Usuario encontrado o vacío si no existe.
     */
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }


    public List<Usuario> obtenerProfesores() {
        return usuarioRepository.findByTipo("Profesor");
    }

    public List<Usuario> obtenerAlumnos() {
        return usuarioRepository.findByTipo("Alumno");
    }

    /**
     * Guardar o actualizar un usuario.
     * @param usuario Usuario a guardar.
     * @return Usuario guardado.
     */
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Eliminar un usuario por su ID.
     * @param usuarioId ID del usuario a eliminar.
     */
    public void eliminar(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new IllegalArgumentException("El usuario con idUsuUni " + usuarioId + " no existe. Aquiii");
        }
        usuarioRepository.deleteById(usuarioId);
    }

    /**
     * Registrar un nuevo usuario verificando duplicados.
     * @param usuario Usuario a registrar.
     * @return Usuario registrado.
     * @throws UsuarioDuplicadoException Si el email o el ID universitario ya están registrados.
     */
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new UsuarioDuplicadoException("El Email ya está registrado.");
        }
        if (usuarioRepository.findByIdUsuUni(usuario.getIdUsuUni()).isPresent()) {
            throw new UsuarioDuplicadoException("El ID universitario ya está registrado.");
        }
        if (usuarioRepository.findByCedula(usuario.getCedula()).isPresent()) {
            throw new UsuarioDuplicadoException("La Cedula ya está registrada.");
        }
        return usuarioRepository.save(usuario);
    }

    /**
     * Actualizar un usuario existente.
     * @param id ID del usuario a actualizar.
     * @param usuarioDTO Datos del usuario para actualizar.
     * @return Usuario actualizado.
     */
    public Usuario actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        // Buscar el usuario existente por ID
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (!usuarioOpt.isPresent()) {
            throw new IllegalArgumentException("El usuario con ID " + id + " no existe.");
        }

        Usuario usuarioExistente = usuarioOpt.get();

        // Actualizar los campos comunes
        usuarioExistente.setTipo(usuarioDTO.getTipo());
        usuarioExistente.setIdUsuUni(usuarioDTO.getIdUsuUni());
        usuarioExistente.setCedula(usuarioDTO.getCedula());
        usuarioExistente.setNombre(usuarioDTO.getNombre());
        usuarioExistente.setEmail(usuarioDTO.getEmail());
        usuarioExistente.setFacultadId(usuarioDTO.getFacultadId());
        usuarioExistente.setActivo(usuarioDTO.getActivo());
        usuarioExistente.setFechaNacimiento(usuarioDTO.getFechaNacimiento());


        // Actualizar campos específicos según el tipo de usuario
        if (usuarioExistente instanceof Estudiante && usuarioDTO.getCarrera() != null) {
            ((Estudiante) usuarioExistente).setCarrera(usuarioDTO.getCarrera());
        } else if (usuarioExistente instanceof Profesor && usuarioDTO.getEspecialidad() != null) {
            ((Profesor) usuarioExistente).setEspecialidad(usuarioDTO.getEspecialidad());
        } else if (usuarioExistente instanceof Administrativo && usuarioDTO.getArea() != null) {
            ((Administrativo) usuarioExistente).setArea(usuarioDTO.getArea());
        }

        // Guardar los cambios en la base de datos
        return usuarioRepository.save(usuarioExistente);
    }
}
