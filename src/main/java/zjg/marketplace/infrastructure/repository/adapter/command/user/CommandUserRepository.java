package zjg.marketplace.infrastructure.repository.adapter.command.user;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.logging.services.Logger;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.infrastructure.repository.interfaces.UserReactiveMongoRepository;
import zjg.marketplace.infrastructure.repository.utils.RepositoryMessageUtils;

@Repository
public class CommandUserRepository implements CommandRepository<User> {
    // * Properties ------------------------------------------------------------------------
    private final UserReactiveMongoRepository jpaRepository;
    private final Logger logger;
    private static final Class<CommandUserRepository> self = CommandUserRepository.class;

    // * Constructors ------------------------------------------------------------------------
    public CommandUserRepository(UserReactiveMongoRepository jpaRepository, Logger logger) {
        this.jpaRepository = jpaRepository;
        this.logger = logger;
    }

    // * Overrides ------------------------------------------------------------------------
    @Override
    public Mono<User> insert(User entity) {
        logger.info(self, RepositoryMessageUtils.infoInsert(entity.getId()));
        return jpaRepository.insert(entity)
                .doOnNext(o -> logger.info(self, RepositoryMessageUtils.successDelete(entity.getId())))
                .doOnError(x -> logger.error(self, RepositoryMessageUtils.errorInsert(entity.getId(), x.getMessage()), x));
    }

    @Override
    public Mono<User> update(User updated) {
        logger.info(self, RepositoryMessageUtils.infoUpdate(updated.getId()));
        return jpaRepository.save(updated)
                .doOnNext(o -> logger.info(self, RepositoryMessageUtils.successDelete(updated.getId())))
                .doOnError(x -> logger.error(self, RepositoryMessageUtils.errorUpdate(updated.getId(), x.getMessage()), x));

    }

    @Override
    public Mono<Void> delete(String id) {
        logger.info(self, RepositoryMessageUtils.infoInsert(id));
        return jpaRepository.deleteById(id)
                .doOnNext(o -> logger.info(self, RepositoryMessageUtils.successDelete(id)))
                .doOnError(x -> logger.error(self, RepositoryMessageUtils.errorDelete(id, x.getMessage()), x));
    }
}
