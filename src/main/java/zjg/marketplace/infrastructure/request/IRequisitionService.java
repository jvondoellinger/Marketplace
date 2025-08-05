package zjg.marketplace.infrastructure.request;

import com.mercadopago.net.HttpMethod;
import reactor.core.publisher.Mono;
import java.net.URISyntaxException;

public interface IRequisitionService {
    Mono<Void> post(String url, Object obj);
}
