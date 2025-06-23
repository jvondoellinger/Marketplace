package zjg.marketplace.application.service.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.anotation.BadCode;
import zjg.marketplace.api.input.ProductInput;
import zjg.marketplace.api.input.ProductWithImageInput;
import zjg.marketplace.application.mapper.ProductMapper;
import zjg.marketplace.core.entity.Product;
import zjg.marketplace.core.factory.ProductFactory;
import zjg.marketplace.repository.ProductRepository;
import zjg.marketplace.application.service.promisse.StorageService;

import java.util.Objects;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final StorageService storageService;
    private final ProductFactory factory;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository,
                          StorageService storageService,
                          ProductFactory factory,
                          ProductMapper mapper) {
        this.repository = repository;
        this.storageService = storageService;
        this.factory = factory;
        this.mapper = mapper;
    }

    public final Mono<Product> create(ProductWithImageInput input) {
        String fileName = UUID.randomUUID().toString();
        return storageService.upload(input.getFile(), fileName)
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
                    var mapped = mapper.map(input, UUID.randomUUID().toString());
                    var updated = original.updateFrom(mapped);
                    return storageService.upload(input.getFile(), updated.getImagePath())
                            .then(Mono.just(updated));
                });
    };

    private final Mono<Product> update(Product original, Product mapped) {
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
}
