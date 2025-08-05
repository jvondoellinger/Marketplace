package zjg.marketplace.infrastructure.repository.impl;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.interfaces.services.repository.IRepository;
import zjg.marketplace.infrastructure.repository.spring.ProductReactiveMongoRepository;

@Repository
public class ProductRepository implements IRepository<Product> {
    public final ProductReactiveMongoRepository repositoryLib;
    private final ReactiveMongoTemplate template;

    public ProductRepository(ProductReactiveMongoRepository repositoryLib, ReactiveMongoTemplate template) {
        this.repositoryLib = repositoryLib;
        this.template = template;
    }


    @Override
    public Mono<Product> insert(Product entity) {
        return repositoryLib.insert(entity);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repositoryLib.deleteById(id);
    }

    @Override
    public Mono<Product> findById(String id) {
        return repositoryLib.findById(id);
    }

    @Override
    public Flux<Product> findWithPagination(Long offset, Integer limit) {
        Query query = new Query().skip(offset).limit(limit);
        return template.find(query, Product.class);
    }

    @Override
    public Mono<Product> update(Product updated) {
        return repositoryLib.save(updated);
    }
}
