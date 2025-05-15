package Asistencias.config;

import Asistencias.security.JwtAuthenticationEntryPoint;
import Asistencias.security.JwtAuthenticationFilter;
import Asistencias.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final JwtTokenProvider tokenProvider;

    // Constructor inyectando JwtAuthenticationEntryPoint y JwtTokenProvider
    public SecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler,
            JwtTokenProvider tokenProvider) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        // Ahora tokenProvider está definido en el ámbito de la clase
        return new JwtAuthenticationFilter(tokenProvider);
    }

        @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
          .cors().and()
          .csrf(csrf -> csrf.disable())
          .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(unauthorizedHandler))
          .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          .authorizeHttpRequests(auth -> auth
              // aquí permitimos llamadas anónimas a:
              .requestMatchers(
                  "/api/auth/**",
                  "/api/rfid",           // <<<<<<<<< permiten POST /api/rfid
                  "/api/rfid/**",        // <<<<<<<<< opcional si tuvieras subrutas
                  "/rfid-ws/**",
                  "/rfid-ws",
                  "/rfid-ws/info",
                  "/rfid-ws/info/**"
              ).permitAll()
              .anyRequest().authenticated()
          )
          .cors(cors -> cors
              .configurationSource(request -> {
                  var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                  corsConfig.setAllowedOrigins(java.util.List.of("http://localhost:4200"));
                  corsConfig.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                  corsConfig.setAllowedHeaders(java.util.List.of("*"));
                  corsConfig.setAllowCredentials(true);
                  return corsConfig;
              })
          )
          // Excluimos el handshake SockJS de los filtros JWT
          .securityMatcher(request -> !request.getServletPath().startsWith("/rfid-ws"));

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
