package zjg.marketplace.infrastructure.repository.adapter.command.product;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.infrastructure.repository.interfaces.ProductReactiveMongoRepository;

@Repository
public class CommandProductRepository implements CommandRepository<Product> {
    private final ProductReactiveMongoRepository jpaRepository;

    public CommandProductRepository(ProductReactiveMongoRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Mono<Product> insert(Product entity) {
        return jpaRepository.insert(entity);
    }

    @Override
    public Mono<Product> update(Product updated) {
        return jpaRepository.save(updated);
    }

    @Override
    public Mono<Void> delete(String id) {
        return jpaRepository.deleteById(id);
    }
}
