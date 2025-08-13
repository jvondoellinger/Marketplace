package zjg.marketplace.core.facade;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.entity.product.ProductBuilder;
import zjg.marketplace.core.image.entity.BasicImageFile;
import zjg.marketplace.core.image.service.StorageService;
import zjg.marketplace.core.image.valueObj.path.ImagePath;
import zjg.marketplace.core.image.valueObj.path.ImagePathListFactory;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.products.chain.ProductUpdaterHandleFactory;

import java.util.List;


public class ProductImageUploadFacade {
    private final CommandRepository<Product> repository;
    private final StorageService storageService;

    public ProductImageUploadFacade(CommandRepository<Product> repository, StorageService storageService) {
        this.repository = repository;
        this.storageService = storageService;
    }
    public Mono<Product> uploadImageInEntity(Product product, BasicImageFile file) {
        return storageService.upload(file)
                .flatMap(last -> {
                    product.getPaths().add(last.getPath());
                    return repository.update(product);
                });
    }

    public Mono<Product> uploadImageInEntity(Product product, List<BasicImageFile> files) {
        var handler = ProductUpdaterHandleFactory.factory();
        return Flux.fromIterable(files)
                .flatMap(storageService::upload)
                .map(BasicImageFile::getPath)
                .map(ImagePath::getCompletePath)
                .collectList()
                .map(ImagePathListFactory::factory)
                .flatMap(paths -> {
                    var p = ProductBuilder.builder().path(paths).build();
                    handler.handle(product, p);
                    return repository.update(product);
                });
    }

    public Mono<Product> removeImage(Product product, ImagePath path) {
        return storageService.remove(path).flatMap(x -> {
            product.getPaths().remove(path);
            return repository.update(product);
        });
    }

    public Mono<Product> removeImage(Product product, List<ImagePath> paths) {
        var cloned = product.clone();
        cloned.getPaths().remove(paths);
        return Flux.fromIterable(paths)
                .flatMap(storageService::remove)
                .then(repository.update(cloned))
                .onErrorMap(x -> new RuntimeException("Cannot do this transaction. Please, try again later!"));
    }

}
