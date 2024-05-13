package Asistencias.controller;
import Asistencias.model.MateriaEstudiante;
import Asistencias.repository.MateriaEstudianteRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/añadirmateriaestudiante")
@CrossOrigin(origins = "http://localhost:4200") 
public class MateriaEstudianteController {

    @Autowired
    private MateriaEstudianteRepository materiaestudianteRepository;

    @GetMapping
    public List<MateriaEstudiante> getMateriaEstudiante() {
        return materiaestudianteRepository.findAll();
    }

    @PostMapping
    public void createMateriaEstudiante(@RequestBody MateriaEstudiante materiaestudiante) {
        materiaestudianteRepository.save(materiaestudiante);
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

