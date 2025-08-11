package zjg.marketplace.infrastructure.repository.adapter.command.order;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.interfaces.services.logging.Logger;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.infrastructure.repository.interfaces.OrderReactiveMongoRepository;
import zjg.marketplace.infrastructure.repository.utils.RepositoryMessageUtils;

@Repository
public class CommandOrderRepository implements CommandRepository<Order> {
    private final OrderReactiveMongoRepository jpaRepository;
    private final Logger logger;
    private static final Class<CommandOrderRepository> self = CommandOrderRepository.class;
    public CommandOrderRepository(OrderReactiveMongoRepository jpaRepository, Logger logger) {
        this.jpaRepository = jpaRepository;
        this.logger = logger;
    }

    @Override
    public Mono<Order> insert(Order entity) {
        logger.info(self, RepositoryMessageUtils.infoInsert(entity.getUserId()));
        return jpaRepository.insert(entity)
                .doOnNext(o -> logger.info(self, RepositoryMessageUtils.successInsert(entity.getId())))
                .doOnError(x -> logger.error(self, RepositoryMessageUtils.errorInsert(entity.getId(), x.getMessage()), x));
    }

    @Override
    public Mono<Order> update(Order updated) {
        logger.info(self, RepositoryMessageUtils.infoUpdate(updated.getUserId()));
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
