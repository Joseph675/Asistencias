package Asistencias.controller;

import Asistencias.model.Curso;
import Asistencias.model.CursoPresencial;
import Asistencias.model.CursoVirtual;
import Asistencias.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "http://localhost:4200")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // Obtener todos los cursos
    @GetMapping
    public List<Curso> getAllCursos() {
        return cursoService.getAllCursos();
    }

    // Obtener un curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Integer id) {
        Optional<Curso> curso = cursoService.getCursoById(id);
        return curso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

   
    // Crear un curso (presencial o virtual)
    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        if (curso instanceof CursoPresencial || curso instanceof CursoVirtual) {
            Curso savedCurso = cursoService.saveCurso(curso);
            return ResponseEntity.ok(savedCurso);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Actualizar un curso
    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Integer id, @RequestBody Curso cursoDetails) {
        Optional<Curso> cursoOptional = cursoService.getCursoById(id);
        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();

            // Actualizar campos comunes
            curso.setMateriaPk(cursoDetails.getMateriaPk());
            curso.setProfesorId(cursoDetails.getProfesorId());
            curso.setCicloLectivo(cursoDetails.getCicloLectivo());
            curso.setCuatrimestre(cursoDetails.getCuatrimestre());
            curso.setHorasSemanales(cursoDetails.getHorasSemanales());
            curso.setEstado(cursoDetails.getEstado());

            // Actualizar campos específicos según el tipo de curso
            if (curso instanceof CursoPresencial && cursoDetails instanceof CursoPresencial) {
                ((CursoPresencial) curso).setAula(((CursoPresencial) cursoDetails).getAula());
                ((CursoPresencial) curso).setCapacidad(((CursoPresencial) cursoDetails).getCapacidad());
            } else if (curso instanceof CursoVirtual && cursoDetails instanceof CursoVirtual) {
                ((CursoVirtual) curso).setPlataforma(((CursoVirtual) cursoDetails).getPlataforma());
                ((CursoVirtual) curso).setEnlaceAcceso(((CursoVirtual) cursoDetails).getEnlaceAcceso());
            }

            Curso updatedCurso = cursoService.saveCurso(curso);
            return ResponseEntity.ok(updatedCurso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Integer id) {
        if (cursoService.getCursoById(id).isPresent()) {
            cursoService.deleteCurso(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}