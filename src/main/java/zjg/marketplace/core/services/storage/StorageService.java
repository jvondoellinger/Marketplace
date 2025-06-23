package zjg.marketplace.core.services.storage;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.anotation.ImplementsInFuture;
import zjg.marketplace.core.entity.file.BasicImageFile;
import zjg.marketplace.core.entity.product.Product;

import java.util.List;
import java.util.function.Supplier;

@Deprecated
@ImplementsInFuture
public class StorageService {
    private final zjg.marketplace.core.interfaces.storage.StorageService storageService;

    public StorageService(zjg.marketplace.core.interfaces.storage.StorageService storageService) {
        this.storageService = storageService;
    }

    public Flux<BasicImageFile> upload(BasicImageFile file, Supplier<Product> supplier) {
        return storageService.upload(file).then(Mono.just(file)).flux();
    }
    public Flux<BasicImageFile> upload(List<BasicImageFile> files, Supplier<Product> supplier) {
        return Flux.fromIterable(files)
                .flatMap(file -> upload(file, supplier));
    }
}
