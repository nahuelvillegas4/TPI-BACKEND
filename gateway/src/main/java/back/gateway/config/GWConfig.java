package back.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GWConfig {
    
    @Value("${url.servicio.logistica}")
    private String logisticaServiceUrl;
    @Value("${url.servicio.pedidos}")
    private String pedidosServiceUrl;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("logistica_route", r -> r
                .path("/logistica/**")
                .uri(logisticaServiceUrl))
            .route("pedidos_route", r -> r
                .path("/pedidos/**")
                .uri(pedidosServiceUrl))
            .build();
    }
}
