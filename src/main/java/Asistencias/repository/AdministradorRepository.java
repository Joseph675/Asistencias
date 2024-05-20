package Asistencias.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import Asistencias.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Administrador findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM administrador WHERE id_administrador = :idAdministrador", nativeQuery = true)
    List<Administrador> findByIdEstudiante(Long idAdministrador);
}
