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
                                     @Value("${url.servicio.pedidos}") String uriPedidos) {
        return builder.routes()

            // Ruteo al microservicio de pedidos
            .route("pedidos", r -> r
                // Aquí definís el path de entrada por el gateway...
                .path("/pedidos/**")
                // ...y lo rediriges a http://localhost:8089
                .uri(uriPedidos)
            )

            .build();
    }
}

