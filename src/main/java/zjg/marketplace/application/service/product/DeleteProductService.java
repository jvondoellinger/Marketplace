package zjg.marketplace.application.service.product;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IDeleteService;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.interfaces.services.repository.Repository;

@Service
public class DeleteProductService implements IDeleteService<Product> {
    private final Repository<Product> repository;

    public DeleteProductService(Repository<Product> repository) {
        this.repository = repository;
    }

    @Override
    @CacheEvict(value = "product", key = "#id")
    public Mono<Void> delete(Product product) {
        return repository.delete(product.getId());
    }

    @Override
    @CacheEvict(value = "product", key = "#id")
    public Mono<Void> deleteById(String id) {
        return repository.delete(id);
    }
}
