package zjg.marketplace.infrastructure.repository.adapter.query.user;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.logging.services.Logger;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.user.repository.exceptions.UserNotFoundException;
import zjg.marketplace.core.user.repository.query.UserRepositoryQuery;
import zjg.marketplace.infrastructure.repository.interfaces.UserReactiveMongoRepository;
import zjg.marketplace.infrastructure.repository.utils.RepositoryMessageUtils;

@Repository
public class UserRepositoryQueryImpl implements UserRepositoryQuery {
    private final UserReactiveMongoRepository jpaRepository;
    private final ReactiveMongoTemplate template;
    private static final Class<UserRepositoryQueryImpl> self = UserRepositoryQueryImpl.class;
    private static final Mono<User> notFoundError = Mono.error(new UserNotFoundException("Any users is found by this identifier!"));
    private final Logger logger;
    public UserRepositoryQueryImpl(UserReactiveMongoRepository jpaRepository, ReactiveMongoTemplate template, Logger logger) {
        this.jpaRepository = jpaRepository;
        this.template = template;
        this.logger = logger;
    }

    @Override
    public Mono<User> findById(String id) {
        logger.info(self, RepositoryMessageUtils.infoQuery(id));
        return jpaRepository.findById(id)
                .switchIfEmpty(notFoundError)
                .doOnNext(entity -> logger.info(self, RepositoryMessageUtils.successQuery(id)))
                .doOnError(x -> logger.error(self, RepositoryMessageUtils.errorQuery(id, x.getMessage())));
    }

    @Override
    public Mono<Boolean> exists(String id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public Flux<User> findWithPagination(long offset, int limit) {
        logger.info(self, RepositoryMessageUtils.infoQueryByOffsetAndLimit(offset, limit));
        var query = new Query().skip(offset).limit(limit);
        return template.find(query, User.class)
                .doOnNext( x -> logger.info(self, RepositoryMessageUtils.successQueryByOffsetAndLimit(offset, limit)))
                .doOnError( x -> logger.error(self, RepositoryMessageUtils.errorQueryByOffsetAndLimit(offset, limit)));
    }

    @Override
    public Mono<User> find(String email) {
        return null;
    }
}
