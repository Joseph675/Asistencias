package Asistencias.repository;

import Asistencias.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Ejemplo de método personalizado para buscar por email
    Optional<Usuario> findByEmail(String email);
    
    // Otros métodos personalizados se pueden agregar según la necesidad,
    // por ejemplo, para filtrar por nombre, o para obtener solo ciertos tipos de usuario.
}
