package Asistencias.controller;

import Asistencias.dto.UsuarioDTO;
import Asistencias.model.Usuario;
import Asistencias.repository.UsuarioRepository;
import Asistencias.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Buscar el usuario por email
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginRequest.getEmail());
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // Verificar la contraseña encriptada
            if (passwordEncoder.matches(loginRequest.getPassword(), usuario.getPasswordHash())) {
                // Generar el token JWT
                String token = jwtTokenProvider.createToken(usuario.getEmail(), usuario.getTipo());
                return ResponseEntity.ok(new AuthResponse(token));
            }
        }
        // Si no se encuentra el usuario o la contraseña no coincide
        return ResponseEntity.status(401).body("Credenciales inválidas");
    }

    // Clases internas solo para ejemplo
    public static class LoginRequest {
        private String email;
        private String password;

        // Getters y setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
}
