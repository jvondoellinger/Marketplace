package zjg.marketplace.application.service.product;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IFindService;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.interfaces.services.repository.Repository;

@Service
public class FindProductService implements IFindService<Product> {
    private final Repository<Product> repository;

    public FindProductService(Repository<Product> repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(value = "product", key = "#offset + '-' + #limit")
    public Flux<Product> get(Long offset, Integer limit) {
        return repository.findWithPagination(offset, limit);
    }

    @Override
    @Cacheable(value = "product", key = "#id")
    public Mono<Product> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Product> findByIdNoCache(String id) {
        return repository.findById(id);
    }
}
