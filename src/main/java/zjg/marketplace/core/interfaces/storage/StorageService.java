package zjg.marketplace.core.interfaces.storage;

import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StorageService {
    Mono<String> upload(FilePart file, String fileName);
    Mono<Void> remove(Resource file);
    Mono<byte[]> get(String name);
}
