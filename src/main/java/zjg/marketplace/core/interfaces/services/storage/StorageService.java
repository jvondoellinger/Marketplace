package zjg.marketplace.core.interfaces.services.storage;


import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.file.BasicImageFile;
import zjg.marketplace.core.valueObject.path.ImagePath;

public interface StorageService {
    Mono<BasicImageFile> upload(BasicImageFile file);
    Mono<Void> remove(ImagePath path);
    Mono<byte[]> get(ImagePath path);
}
