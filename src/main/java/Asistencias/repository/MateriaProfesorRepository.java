package Asistencias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import Asistencias.model.MateriaProfesor;

public interface MateriaProfesorRepository extends JpaRepository<MateriaProfesor, Long> {

    @Modifying
    @Transactional
    @Query("delete from MateriaProfesor me where me.id_profesor = ?1")
    void deleteByIdProfesor(Long id);

    @Modifying
    @Query("delete from MateriaProfesor em where em.id_materia = :idMateria")
    void deleteByIdMateria(@Param("idMateria") Long idMateria);

    @Query(value = "SELECT * FROM profesores_materias WHERE id_profesor = :idProfesor AND id_materia = :idMateria AND hora = :hora AND dias = :dias", nativeQuery = true)
    List<MateriaProfesor> findByIdProfesorAndIdMateriaAndHoraAndDias(int idProfesor, int idMateria, String hora, String dias);

    @Query(value = "SELECT * FROM profesores_materias WHERE id_profesor = :idProfesor", nativeQuery = true)
    List<MateriaProfesor> findByIdProfesor(Long idProfesor);

}
