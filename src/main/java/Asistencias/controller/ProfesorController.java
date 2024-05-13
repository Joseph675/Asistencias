package Asistencias.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Asistencias.model.Profesor;
import Asistencias.repository.ProfesorRepository;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path = "/api/profesores")
@CrossOrigin(origins = "http://localhost:4200") 
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping
    public List<Profesor> getProfesores() {
        return profesorRepository.findAll();
    }

    @PostMapping
    public void createProfesor(@RequestBody Profesor profesor) {
        profesorRepository.save(profesor);
    }

    @DeleteMapping("/{id}")
    public void deleteProfesor(@PathVariable Long id) {
        profesorRepository.deleteById(id);
    }


    @PutMapping("/{id}")
    public Profesor updateProfesor(@PathVariable Long id, @RequestBody Profesor profesorDetails) {
        Profesor profesor = profesorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Profesor no encontrado con id :" + id));

            profesor.setNombre(profesorDetails.getNombre());
            profesor.setApellido(profesorDetails.getApellido());

        Profesor updatedProfesor = profesorRepository.save(profesor);
        return updatedProfesor;
    }

}
