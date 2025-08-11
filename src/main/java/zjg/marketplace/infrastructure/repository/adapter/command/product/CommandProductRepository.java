package zjg.marketplace.infrastructure.repository.adapter.command.product;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.interfaces.services.logging.Logger;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.infrastructure.repository.interfaces.ProductReactiveMongoRepository;
import zjg.marketplace.infrastructure.repository.utils.RepositoryMessageUtils;

@Repository
public class CommandProductRepository implements CommandRepository<Product> {
    private final ProductReactiveMongoRepository jpaRepository;
    private final Logger logger;
    private static final Class<CommandProductRepository> self = CommandProductRepository.class;
    public CommandProductRepository(ProductReactiveMongoRepository jpaRepository, Logger logger) {
        this.jpaRepository = jpaRepository;
        this.logger = logger;
    }

    @Override
    public Mono<Product> insert(Product entity) {
        logger.info(self, RepositoryMessageUtils.infoInsert(entity.getId()));
        return jpaRepository.insert(entity)
                .doOnNext(o -> logger.info(self, RepositoryMessageUtils.successInsert(entity.getId())))
                .doOnError(x -> logger.error(self, RepositoryMessageUtils.errorInsert(entity.getId(), x.getMessage()), x));
    }

    @Override
    public Mono<Product> update(Product updated) {
        logger.info(self, RepositoryMessageUtils.infoUpdate(updated.getId()));
        return jpaRepository.save(updated)
                .doOnNext(o -> logger.info(self, RepositoryMessageUtils.successUpdate(updated.getId())))
                .doOnError(x -> logger.error(self, RepositoryMessageUtils.errorUpdate(updated.getId(), x.getMessage()), x));
    }

    @Override
    public Mono<Void> delete(String id) {
        logger.info(self, RepositoryMessageUtils.infoDelete(id));
        return jpaRepository.deleteById(id)
                .doOnNext(o -> logger.info(self, RepositoryMessageUtils.successDelete(id)))
                .doOnError(x -> logger.error(self, RepositoryMessageUtils.errorDelete(id, x.getMessage()), x));
    }
}
