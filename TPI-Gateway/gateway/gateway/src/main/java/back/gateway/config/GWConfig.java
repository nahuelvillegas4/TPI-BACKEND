package back.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GWConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder,
                                     @Value("${url.servicio.admin}") String uriAdmin) {
        return builder.routes()
            // Ruteo al servicio de logistica
            .route("admin", r -> r
                .path("/api/depositos/**", "/api/camiones/**")              // cualquier /api/depositos o /api/depositos/{id}
                .uri(uriAdmin)                      // e.g. http://localhost:8080
            )
            
            // Ruteo al servicio PRUEBA

            .build();
    }
}
