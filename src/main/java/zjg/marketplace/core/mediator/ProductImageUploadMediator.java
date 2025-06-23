package zjg.marketplace.core.mediator;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.file.BasicImageFile;
import zjg.marketplace.core.field.path.CustomPath;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.field.extension.ImageExtension;
import zjg.marketplace.core.interfaces.repository.IRepository;
import zjg.marketplace.core.interfaces.storage.StorageService;

import java.util.List;

@Service // Remover - Não deve ficar no core!
public class ProductImageUploadMediator {
    private final IRepository<Product> repository;
    private final StorageService storageService;

    public ProductImageUploadMediator(IRepository<Product> repository, StorageService storageService) {
        this.repository = repository;
        this.storageService = storageService;
    }

    public Mono<Product> uploadImage(Product product, byte[] bytes, ImageExtension extension) {
        var path = new CustomPath(extension, product.getId());
        var file = new BasicImageFile(bytes, path);
        product.getPaths().add(path);
        return storageService.upload(file)
                .map(x -> {
                    product.addPath(x.getPath());
                    return product;
                });
    }
    public Mono<Product> uploadImage(Product product, List<byte[]> bytes, ImageExtension extension) {
        return Flux.fromIterable(bytes)
                .flatMap(b -> {
                    var path = new CustomPath(extension, product.getId());
                    var file = new BasicImageFile(b, path);
                    return storageService.upload(file);
                })
                .map(BasicImageFile::getPath)
                .collectList()
                .flatMap(paths -> {
                    product.addBatchPath(paths);
                    return repository.update(product);
                });
    }

    public Mono<Product> removeImage(Product product, CustomPath path) {
        return storageService.remove(path).flatMap(x -> {
            product.removePath(path);
            return repository.update(product);
        });
    }
    public Mono<Product> removeImage(Product product, List<CustomPath> paths) {
        var cloned = product.clone();
        cloned.removeBatchPath(paths);
        return Flux.fromIterable(paths)
                .flatMap(storageService::remove)
                .then(repository.update(cloned))
                .onErrorMap(x -> new RuntimeException("Cannot do this transaction. Please, try again later!"));
    }
}
