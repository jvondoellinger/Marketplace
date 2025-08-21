package zjg.marketplace.infrastructure.repository.adapter.query.user;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.user.repository.exceptions.UserNotFoundException;
import zjg.marketplace.core.logging.services.Logger;
import zjg.marketplace.core.interfaces.services.repository.query.QueryByEmailRepository;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.infrastructure.repository.interfaces.UserReactiveMongoRepository;
import zjg.marketplace.infrastructure.repository.utils.RepositoryMessageUtils;

@Repository
public class QueryUserByEmailRepositoryImpl implements QueryByEmailRepository<User> {
    private final UserReactiveMongoRepository jpaRepository;
    private final Logger logger;
    private static final Class<QueryUserByEmailRepositoryImpl> self = QueryUserByEmailRepositoryImpl.class;
    private static final Mono<User> notFoundError = Mono.error(new UserNotFoundException("Any user is found by this email!"));
    public QueryUserByEmailRepositoryImpl(UserReactiveMongoRepository jpaRepository, Logger logger) {
        this.jpaRepository = jpaRepository;
        this.logger = logger;
    }

    @Override
    public Mono<User> find(String email) {
        logger.info(self, RepositoryMessageUtils.infoQuery(email));
        return jpaRepository.findByEmail(email)
                .switchIfEmpty(notFoundError)
                .doOnNext(x -> logger.info(self, RepositoryMessageUtils.successQuery(email)))
                .doOnError(ex -> logger.error(self, RepositoryMessageUtils.errorQuery(email, ex.getMessage()), ex));
    }
}
