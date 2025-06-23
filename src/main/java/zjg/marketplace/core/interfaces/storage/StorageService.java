package zjg.marketplace.core.interfaces.storage;


import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.file.BasicImageFile;
import zjg.marketplace.core.field.path.CustomPath;

public interface StorageService {
    Mono<BasicImageFile> upload(BasicImageFile file);
    Mono<Void> remove(CustomPath path);
    Mono<byte[]> get(CustomPath path);
}
