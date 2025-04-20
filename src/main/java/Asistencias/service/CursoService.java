package Asistencias.service;

import Asistencias.model.Curso;
import Asistencias.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    // Obtener todos los cursos
    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    // Obtener un curso por ID
    public Optional<Curso> getCursoById(Integer id) {
        return cursoRepository.findById(id);
    }

    // Guardar un curso (puede ser presencial o virtual)
    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Eliminar un curso por ID
    public void deleteCurso(Integer id) {
        cursoRepository.deleteById(id);
    }
}