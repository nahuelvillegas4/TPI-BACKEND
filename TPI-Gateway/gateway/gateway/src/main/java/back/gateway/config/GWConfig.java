@Configuration
public class GWConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder,
                                     @Value("${url.servicio.pedidos}") String uriPedidos,
                                     @Value("${url.servicio.logistica}") String uriLogistica) {
        return builder.routes()
                .route(p -> p.path("/api/solicitudes/**", "/api/seguimiento/**")
                        .uri(uriPedidos))
                .route(p -> p.path("/api/tarifas/**", "/api/logistica/**")
                        .uri(uriLogistica))
                .build();
    }
}
