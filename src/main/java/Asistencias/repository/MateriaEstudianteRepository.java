package Asistencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import Asistencias.model.MateriaEstudiante;

public interface MateriaEstudianteRepository extends JpaRepository<MateriaEstudiante, Long> {

    @Modifying
    @Transactional
    @Query("delete from MateriaEstudiante me where me.id_estudiante = ?1")
    void deleteByIdEstudiante(Long id);

    @Query(value = "delete from estudiantes_materias where id_materia = ?1", nativeQuery = true)
    void deleteByIdMateria(Long idMateria);

    @Query(value = "SELECT * FROM estudiantes_materias WHERE id_estudiante = :idEstudiante AND id_materia = :idMateria AND hora = :hora AND dias = :dias", nativeQuery = true)
    List<MateriaEstudiante> findByIdEstudianteAndIdMateriaAndHoraAndDias(int idEstudiante, int idMateria, String hora, String dias);

}


