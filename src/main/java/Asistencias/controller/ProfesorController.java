package Asistencias.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.server.ResponseStatusException;

import Asistencias.JwtTokenProvider;
import Asistencias.model.Profesor;
import Asistencias.repository.ProfesorRepository;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path = "/api/profesores")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public List<Profesor> getProfesores() {
        return profesorRepository.findAll();
    }

    

    @GetMapping("/porProfesor/{id_profesor}")
    public List<Profesor> getProfesorConId(@PathVariable Long id_profesor) {
        return profesorRepository.findByIdProfesor(id_profesor);
    }


    @PostMapping
    public ResponseEntity<?> createProfesor(@RequestBody Profesor profesor) {
        // Buscar si el estudiante ya existe en la base de datos
        Profesor profesorExistente = profesorRepository.findByEmail(profesor.getEmail());

        // Si el estudiante ya existe, devolver un mensaje de error
        if (profesorExistente != null) {
            return new ResponseEntity<>("El estudiante ya existe en la base de datos", HttpStatus.CONFLICT);
        }

        // Si el estudiante no existe, guardar el nuevo estudiante en la base de datos
        profesorRepository.save(profesor);
        return new ResponseEntity<>(profesor, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteProfesor(@PathVariable Long id) {
        profesorRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Profesor updateProfesor(@PathVariable Long id, @RequestBody Profesor profesorDetails) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profesor no encontrado con id :" + id));

        // Comprueba si el correo electrónico ya existe
        Profesor existingProfesor = profesorRepository.findByEmail(profesorDetails.getEmail());
        if (existingProfesor != null && !existingProfesor.getId_profesor().equals(id)) {
            throw new IllegalArgumentException("El correo electrónico ya está en uso");
        }

        profesor.setNombre(profesorDetails.getNombre());
        profesor.setApellido(profesorDetails.getApellido());
        profesor.setEmail(profesorDetails.getEmail());

        Profesor updateProfesor = profesorRepository.save(profesor);
        return updateProfesor;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Profesor loginRequest) {
        // Buscar el estudiante en la base de datos
        Profesor profesor = profesorRepository.findByEmail(loginRequest.getEmail());

        // Comprobar si el estudiante existe y la contraseña es correcta
        if (profesor != null && loginRequest.getpassword().equals(profesor.getpassword())) {
            // Inicio de sesión exitoso
            // Generar el token JWT
            String token = jwtTokenProvider.createToken(loginRequest.getEmail(), profesor.getrol());

            // Imprimir el token en la consola
            System.out.println("Token generado: " + token);

            // Crear un mapa para la respuesta
            Map<Object, Object> model = new HashMap<>();
            model.put("id", profesor.getId_profesor()); // Aquí está el cambio
            model.put("nombre", profesor.getNombre()); // Aquí está el cambio
            model.put("email", profesor.getEmail()); // Aquí está el cambio
            model.put("avatar", profesor.getAvatar()); // Aquí está el cambio
            model.put("rol", profesor.getrol()); // Aquí está el cambio
            model.put("token", token);

            // Devolver el token en la respuesta
            return ResponseEntity.ok(model);
        } else {
            // Inicio de sesión fallido
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Correo electrónico o contraseña incorrectos");
        }
    }

}
