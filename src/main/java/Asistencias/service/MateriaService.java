package Asistencias.service;

import Asistencias.model.Materia;
import Asistencias.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    public List<Materia> getAllMaterias() {
        return materiaRepository.findAll();
    }

    public Optional<Materia> getMateriaById(Integer id) {
        return materiaRepository.findById(id);
    }

    public Materia saveMateria(Materia materia) {
        return materiaRepository.save(materia);
    }

    public void deleteMateria(Integer id) {
        materiaRepository.deleteById(id);
    }
}