package Asistencias.service;

import Asistencias.model.Inscripciones;
import Asistencias.repository.InscripcionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionesService {

    @Autowired
    private InscripcionesRepository inscripcionesRepository;

    public List<Inscripciones> getAllInscripciones() {
        return inscripcionesRepository.findAll();
    }

    public Optional<Inscripciones> getInscripcionById(Long id) {
        return inscripcionesRepository.findById(id);
    }

    public Inscripciones saveInscripcion(Inscripciones inscripcion) {
        return inscripcionesRepository.save(inscripcion);
    }

    public void deleteInscripcion(Long id) {
        inscripcionesRepository.deleteById(id);
    }
}