package zjg.marketplace.infrastructure.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class RequestService implements RequisitionService {
    private final ObjectMapper mapper;

    public RequestService() {
        this.mapper = new ObjectMapper();
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
            return response
                    .then()
                    .timeout(Duration.ofSeconds(15));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
