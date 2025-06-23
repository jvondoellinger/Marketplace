package zjg.marketplace.application.service.promisse;

import org.springframework.core.io.Resource;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface StorageService {
    Mono<Void> upload(FilePart file, String fileName);
    Mono<Void> remove(Resource file);
    Mono<Resource> get(String name);
}
