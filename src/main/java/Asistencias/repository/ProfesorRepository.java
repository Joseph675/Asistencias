package Asistencias.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import Asistencias.model.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Profesor findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM profesores WHERE id_profesor = :IdProfesor", nativeQuery = true)
    List<Profesor> findByIdProfesor(Long IdProfesor);
}
