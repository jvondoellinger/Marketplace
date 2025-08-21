package zjg.marketplace.core.image.service;


import reactor.core.publisher.Mono;
import zjg.marketplace.core.image.entity.BasicImageFile;
import zjg.marketplace.core.image.valueObj.path.ImagePath;

public interface StorageService {
    Mono<BasicImageFile> upload(BasicImageFile file);
    Mono<Void> remove(ImagePath path);
    Mono<byte[]> get(ImagePath path);
}
