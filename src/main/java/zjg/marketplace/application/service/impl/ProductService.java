package zjg.marketplace.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IBasicService;
import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.interfaces.repository.IRepository;
import zjg.marketplace.presentation.input.ProductInput;
import zjg.marketplace.application.mapper.ProductMapper;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.infrastructure.repository.spring.ProductReactiveMongoRepository;

import java.util.List;


@Service
public class ProductService implements IBasicService<Product, ProductInput> {
    private final IRepository<Product> repository;
    private final StorageServiceImpl storageService;
    private final ProductMapper mapper;
    @Autowired
    public ProductService(IRepository<Product> repository,
                          StorageServiceImpl storageService,
                          ProductMapper mapper) {
        this.repository = repository;
        this.storageService = storageService;
        this.mapper = mapper;
    }
    // Criar uma classe Path que gerencia isso!
    @Cacheable(value = "product", key = "#id")
    public Mono<Product> create(ProductInput input) {
            var product = mapper.map(input);
            return repository.insert(product);
    }

    @BadCode
    @CacheEvict(value = "product", key = "#id")
    public Mono<Product> update(ProductInput input, String id) {
        return findById(id)
                .flatMap(original -> {
                    var partial = mapper.map(input);
                    var updated = original.selfUpdate(partial);
                    return repository.update(updated);
                });
    }

    public Mono<Void> delete(String identifier) {
        return repository.delete(identifier);
    }

    @Cacheable(value = "product", key = "#id", unless = "#result == null")
    public Mono<Product> findById(String id) {
        return repository.findById(id);
    }

    @Cacheable(value = "product", key = "#offset", unless = "#result == null")
    public Mono<List<Product>> get(Long offset, Integer limit) {
        return repository.findWithPagination(offset, limit).collectList();
    }
}
