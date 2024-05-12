package Asistencias.controller;
import Asistencias.model.Estudiante;
import Asistencias.repository.EstudianteRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/estudiantes")
@CrossOrigin(origins = "http://localhost:4200") 
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping
    public List<Estudiante> getEstudiantes() {
        return estudianteRepository.findAll();
    }

    @PostMapping
    public void createEstudiante(@RequestBody Estudiante estudiante) {
        estudianteRepository.save(estudiante);
    }

    @DeleteMapping("/{id}")
    public void deleteEstudiante(@PathVariable Long id) {
        estudianteRepository.deleteById(id);
    }


     @PutMapping("/{id}")
    public Estudiante updateEstudiante(@PathVariable Long id, @RequestBody Estudiante estudianteDetails) {
        Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id :" + id));

        estudiante.setNombre(estudianteDetails.getNombre());
        estudiante.setApellido(estudianteDetails.getApellido());

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

