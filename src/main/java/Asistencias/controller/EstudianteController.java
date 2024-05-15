package Asistencias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Asistencias.model.Estudiante;
import Asistencias.repository.EstudianteRepository;
import Asistencias.repository.MateriaEstudianteRepository;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path = "/api/estudiantes")
@CrossOrigin(origins = "http://localhost:4200")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private MateriaEstudianteRepository materiaestudianteRepository;

    @GetMapping
    public List<Estudiante> getEstudiantes() {
        return estudianteRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createEstudiante(@RequestBody Estudiante estudiante) {
        // Buscar si el estudiante ya existe en la base de datos
        Estudiante estudianteExistente = estudianteRepository.findByEmail(estudiante.getEmail());

        // Si el estudiante ya existe, devolver un mensaje de error
        if (estudianteExistente != null) {
            return new ResponseEntity<>("El estudiante ya existe en la base de datos", HttpStatus.CONFLICT);
        }

        // Si el estudiante no existe, guardar el nuevo estudiante en la base de datos
        estudianteRepository.save(estudiante);
        return new ResponseEntity<>(estudiante, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteEstudiante(@PathVariable Long id) {
        // Primero, elimina las filas en estudiantes_materias que hacen referencia al estudiante
        materiaestudianteRepository.deleteByIdEstudiante(id);

        // Luego, elimina el estudiante
        estudianteRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Estudiante updateEstudiante(@PathVariable Long id, @RequestBody Estudiante estudianteDetails) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id :" + id));

        // Comprueba si el correo electrónico ya existe
        Estudiante existingEstudiante = estudianteRepository.findByEmail(estudianteDetails.getEmail());
        if (existingEstudiante != null && !existingEstudiante.getId_estudiante().equals(id)) {
            throw new IllegalArgumentException("El correo electrónico ya está en uso");
        }

        estudiante.setNombre(estudianteDetails.getNombre());
        estudiante.setApellido(estudianteDetails.getApellido());
        estudiante.setEmail(estudianteDetails.getEmail());

        Estudiante updatedEstudiante = estudianteRepository.save(estudiante);
        return updatedEstudiante;
    }

    @PostMapping("/login")
    public Estudiante loginEstudiante(@RequestBody Estudiante estudianteDetails) {
        // Buscar al estudiante en la base de datos
        Estudiante estudiante = estudianteRepository.findByEmail(estudianteDetails.getEmail());

        // Si el estudiante no existe, lanzar un error
        if (estudiante == null) {
            throw new EntityNotFoundException("Estudiante no encontrado con email: " + estudianteDetails.getEmail());
        }

        // Si la password no coincide, lanzar un error
        if (!estudiante.getpassword().equals(estudianteDetails.getpassword())) {
            throw new IllegalArgumentException("password incorrecta");
        }

        // Si las credenciales son correctas, devolver el estudiante
        return estudiante;
    }

}
