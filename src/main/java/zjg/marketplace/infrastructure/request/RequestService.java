package zjg.marketplace.infrastructure.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.net.HttpMethod;
import com.sun.net.httpserver.Headers;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;
import java.time.Duration;

@Service
public class RequestService implements IRequisitionService {
    private final ObjectMapper mapper;

    public RequestService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Mono<Void> post(String url, Object obj) {
        try{
            var json = mapper.writeValueAsString(obj);
            var client = WebClient.create(url);
            var response = client.post()
                    .header("Content-Type", "application/json")
                    .bodyValue(json)
                    .retrieve()
                    .bodyToMono(String.class);
            System.out.println(json);
            return response
                    .then()
                    .timeout(Duration.ofSeconds(15));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
