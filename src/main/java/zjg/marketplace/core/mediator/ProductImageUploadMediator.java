package zjg.marketplace.core.mediator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.file.BasicImageFile;
import zjg.marketplace.core.valueObjects.path.ImagePath;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.interfaces.services.repository.IRepository;
import zjg.marketplace.core.interfaces.services.storage.StorageService;

import java.util.List;


public class ProductImageUploadMediator {
    private final IRepository<Product> repository;
    private final StorageService storageService;

    public ProductImageUploadMediator(IRepository<Product> repository, StorageService storageService) {
        this.repository = repository;
        this.storageService = storageService;
    }
    public Mono<Product> uploadImageInEntity(Product product, BasicImageFile file) {
        return storageService.upload(file)
                .flatMap(last -> {
                    product.addPath(last.getPath());
                    return repository.update(product);
                });
    }

    public Mono<Product> uploadImageInEntity(Product product, List<BasicImageFile> files) {
        return Flux.fromIterable(files)
                .flatMap(storageService::upload)
                .map(BasicImageFile::getPath)
                .collectList()
                .flatMap(paths -> {
                    product.addBatchPath(paths);
                    return repository.update(product);
                });
    }
/*
    public Mono<Product> uploadImageInEntity(Product product, byte[] bytes, ImageExtension extension) {
        var path = ImagePath.getInstance(extension, product.getId());
        var file = new BasicImageFile(bytes, path);
        return storageService.upload(file)
                .flatMap(last -> {
                    List<ImagePath> newList = new ArrayList<>(product.getPaths());
                    newList.add(last.getPath());
                    var size = newList.size();
                    product.setPaths(newList);
                    return repository.update(product);
                });
    }*/

    public Mono<Product> removeImage(Product product, ImagePath path) {
        return storageService.remove(path).flatMap(x -> {
            product.removePath(path);
            return repository.update(product);
        });
    }

    public Mono<Product> removeImage(Product product, List<ImagePath> paths) {
        var cloned = product.clone();
        cloned.removeBatchPath(paths);
        return Flux.fromIterable(paths)
                .flatMap(storageService::remove)
                .then(repository.update(cloned))
                .onErrorMap(x -> new RuntimeException("Cannot do this transaction. Please, try again later!"));
    }

}
