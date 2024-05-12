package Asistencias.controller;
import Asistencias.model.Materia;
import Asistencias.repository.MateriaRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/materias")
@CrossOrigin(origins = "http://localhost:4200") 
public class MateriaController {

    @Autowired
    private MateriaRepository materiaRepository;

    @GetMapping
    public List<Materia> getMaterias() {
        return materiaRepository.findAll();
    }

    @PostMapping
    public void createMateria(@RequestBody Materia materia) {
        materiaRepository.save(materia);
    }

    @DeleteMapping("/{id}")
    public void deleteMateria(@PathVariable Long id) {
        materiaRepository.deleteById(id);
    }


     @PutMapping("/{id}")
    public Materia updateMateria(@PathVariable Long id, @RequestBody Materia materiaDetails) {
        Materia materia = materiaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Materia no encontrado con id :" + id));

        materia.setNombre_materia(materiaDetails.getNombre_materia());
        // Actualiza los demás campos aquí...

        Materia updatedMateria = materiaRepository.save(materia);
        return updatedMateria;
    }
}

