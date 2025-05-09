package Asistencias.service;

import Asistencias.model.Carrera_Materia;
import Asistencias.model.Carrera_MateriaId;
import Asistencias.repository.Carrera_MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Carrera_MateriaService {

    @Autowired
    private Carrera_MateriaRepository carrera_MateriaRepository;

    public List<Carrera_Materia> getAllCarreraMaterias() {
        return carrera_MateriaRepository.findAll();
    }

    public Optional<Carrera_Materia> getCarreraMateriaById(Carrera_MateriaId id) {
        return carrera_MateriaRepository.findById(id);
    }

    public Carrera_Materia saveCarreraMateria(Carrera_Materia carreraMateria) {
        return carrera_MateriaRepository.save(carreraMateria);
    }

    public void deleteCarreraMateria(Carrera_MateriaId id) {
        carrera_MateriaRepository.deleteById(id);
    }
}