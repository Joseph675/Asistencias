package Asistencias.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica a todas las rutas
                .allowedOrigins("http://localhost:4200")  // Permite solicitudes desde este origen
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
// Este código configura CORS para permitir solicitudes desde el origen http://localhost:4200, que es donde se espera que esté corriendo la aplicación Angular.