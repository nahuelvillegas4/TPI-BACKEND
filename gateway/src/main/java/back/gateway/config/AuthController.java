package back.gateway.config;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class AuthController {

    private final WebClient webClient = WebClient.create();

    @GetMapping("/api/login/oauth2/code/keycloak")
    public Mono<String> intercambiarCode(@RequestParam String code) {
        String tokenUrl = "http://localhost:8080/realms/tpi-backend/protocol/openid-connect/token";

        return webClient.post()
            .uri(tokenUrl)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .bodyValue("grant_type=authorization_code" +
                       "&code=" + code +
                       "&client_id=tpi-backend-client-1" +
                       "&redirect_uri=http://localhost:8083/api/login/oauth2/code/keycloak")
            .retrieve()
            .bodyToMono(String.class); // Esto te da la respuesta del token (en JSON)
    }
}