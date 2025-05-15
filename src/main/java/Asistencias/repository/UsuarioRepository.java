package Asistencias.repository;

import Asistencias.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByIdUsuUni(Long idUsuUni);
    Optional<Usuario> findByCedula(Integer cedula);
    List<Usuario> findByTipo(String tipo);

    Optional<Usuario> findByUid(String uid);


}
