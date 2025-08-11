package zjg.marketplace.infrastructure.repository.adapter.query.user;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.exceptions.validations.user.UserNotFoundException;
import zjg.marketplace.core.interfaces.services.logging.Logger;
import zjg.marketplace.core.interfaces.services.repository.query.QueryRepository;
import zjg.marketplace.infrastructure.repository.interfaces.UserReactiveMongoRepository;
import zjg.marketplace.infrastructure.repository.utils.RepositoryMessageUtils;

@Repository
public class QueryUserRepository implements QueryRepository<User> {
    private final UserReactiveMongoRepository jpaRepository;
    private final ReactiveMongoTemplate template;
    private static final Class<QueryUserRepository> self = QueryUserRepository.class;
    private static final Mono<User> notFoundError = Mono.error(new UserNotFoundException("Any users is found by this identifier!"));
    private final Logger logger;
    public QueryUserRepository(UserReactiveMongoRepository jpaRepository, ReactiveMongoTemplate template, Logger logger) {
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
    public Flux<User> findWithPagination(Long offset, Integer limit) {
        logger.info(self, RepositoryMessageUtils.infoQueryByOffsetAndLimit(offset, limit));
        var query = new Query().skip(offset).limit(limit);
        return template.find(query, User.class)
                .doOnNext( x -> logger.info(self, RepositoryMessageUtils.successQueryByOffsetAndLimit(offset, limit)))
                .doOnError( x -> logger.error(self, RepositoryMessageUtils.errorQueryByOffsetAndLimit(offset, limit)));
    }
}
