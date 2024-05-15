package Asistencias.controller;
import Asistencias.model.MateriaEstudiante;
import Asistencias.repository.MateriaEstudianteRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/materiaestudiante")
@CrossOrigin(origins = "http://localhost:4200") 
public class MateriaEstudianteController {

    @Autowired
    private MateriaEstudianteRepository materiaestudianteRepository;

    @GetMapping
    public List<MateriaEstudiante> getMateriaEstudiante() {
        return materiaestudianteRepository.findAll();
    }

    @PostMapping
public ResponseEntity<?> createMateriaEstudiante(@RequestBody MateriaEstudiante materiaestudiante) {
    List<MateriaEstudiante> existingEntries = materiaestudianteRepository.findByIdEstudianteAndIdMateriaAndHoraAndDias(
        materiaestudiante.getId_estudiante(),
        materiaestudiante.getId_materia(),
        materiaestudiante.getHora(),
        materiaestudiante.getDias()
    );

    if (!existingEntries.isEmpty()) {
        return new ResponseEntity<>("Ya existe un registro con los mismos datos.", HttpStatus.CONFLICT);
    }

    materiaestudianteRepository.save(materiaestudiante);
    return new ResponseEntity<>(materiaestudiante, HttpStatus.CREATED);
}


    @DeleteMapping("/{id}")
    public void deleteMateriaEstudiante(@PathVariable Long id) {
        materiaestudianteRepository.deleteById(id);
    }


    @PutMapping("/{id}")
    public MateriaEstudiante updateMateriaEstudiante(@PathVariable Long id, @RequestBody MateriaEstudiante materiaestudianteDetails) {
        MateriaEstudiante materiaestudiante = materiaestudianteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("MateriaEstudiante no encontrado con id :" + id));

        // Aquí puedes actualizar los otros campos de tu objeto materiaestudiante, pero no los IDs si son claves primarias.
        // materiaestudiante.setOtroCampo(materiaestudianteDetails.getOtroCampo());

        MateriaEstudiante updateMateriaEstudiante = materiaestudianteRepository.save(materiaestudiante);
        return updateMateriaEstudiante;
    }


}

