package zjg.marketplace.application.service.impl;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.mapper.BinaryMapper;
import zjg.marketplace.core.entity.file.BasicImageFile;
import zjg.marketplace.core.field.path.CustomPath;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.factory.file.ImageFileFactory;
import zjg.marketplace.core.interfaces.storage.StorageService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;

@Service
public class StorageServiceImpl {
    static final Path path = Paths.get("uploads");
    private final BinaryMapper mapper;
    private final StorageService storageService;

    public StorageServiceImpl(BinaryMapper mapper, StorageService storageService) {
        this.mapper = mapper;
        this.storageService = storageService;
    }


    public Mono<BasicImageFile> upload(FilePart file, String productId) {
        return mapper.toByteArray(file).flatMap(binary -> {
            var image = ImageFileFactory.factory(binary, file.filename(), productId);
            return storageService.upload(image).then(Mono.just(image));
        });
    }

    public Flux<BasicImageFile> upload(List<FilePart> files, String productId) {
        return Flux.fromIterable(files).flatMap(file -> {
            return upload(file, productId);
        });
    }
    public Flux<BasicImageFile> upload(List<FilePart> files, Supplier<Mono<Product>> supplier) {
        var productMono = supplier.get().cache();
        return Flux.fromIterable(files)
                .flatMap(file ->
                        productMono.flatMapMany(p -> upload(file, p.getId()))
                );
    }

    public Mono<Void> remove(CustomPath path) {
        return storageService.remove(path);
    }

    public Mono<byte[]> get(CustomPath path) {
        return storageService.get(path);
    }
}
