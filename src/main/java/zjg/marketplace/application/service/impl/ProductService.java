package zjg.marketplace.application.service.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.anotation.BadCode;
import zjg.marketplace.api.input.ProductInput;
import zjg.marketplace.api.input.ProductWithImageInput;
import zjg.marketplace.application.mapper.ProductMapper;
import zjg.marketplace.core.entity.Product;
import zjg.marketplace.core.utils.PathUtils;
import zjg.marketplace.infrastructure.repository.ProductRepository;
import zjg.marketplace.core.interfaces.storage.StorageService;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final StorageService storageService;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository,
                          StorageService storageService,
                          ProductMapper mapper) {
        this.repository = repository;
        this.storageService = storageService;
        this.mapper = mapper;
    }

    public final Mono<Product> create(ProductWithImageInput input) {
        var filename = PathUtils.modifyName(input.getFile().filename(), UUID.randomUUID().toString());
        return storageService.upload(input.getFile(), filename)
                .map(path -> mapper.map(input, path))
                .flatMap(repository::save);
    }

    @BadCode
    public final Mono<Product> updateFields(ProductInput input, String identifier) {
        return findById(identifier)
                .flatMap(product -> {
                    var mapped = mapper.map(input);
                    return update(product, mapped);
                });
    }

    @BadCode
    public final Mono<Product> updateWithImage(ProductWithImageInput input, String identifier) {
       if(Objects.isNull(input.getFile()))
            return Mono.error(new RuntimeException("Resource cannot be null"));
        return findById(identifier)
                .flatMap(original -> {
                    var path = PathUtils.modifyName(input.getFile().filename(), UUID.randomUUID().toString());
                    var mapped = mapper.map(input, path);
                    var updated = original.updateFrom(mapped);
                    return storageService.upload(input.getFile(), updated.getImagePath())
                            .then(update(original, mapped));
                });
    };

    private Mono<Product> update(Product original, Product mapped) {
        var updated = original.updateFrom(mapped);
        return repository.save(updated);
    }

    public final Mono<Void> delete(String identifier) {
        return repository.deleteById(identifier);
    }

    public final Mono<Product> findById(String identifier) {
        return repository.findById(identifier);
    }

    public final Flux<Product> get(Long offset, Integer limit) {
        return repository.findWithPagination(offset, limit);
    }

    public final Mono<byte[]> getImage(String path) {
        return storageService.get(path);
    }
}
