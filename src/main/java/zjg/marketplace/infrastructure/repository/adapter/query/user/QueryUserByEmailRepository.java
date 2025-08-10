package zjg.marketplace.infrastructure.repository.adapter.query.user;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.interfaces.services.repository.query.QueryByEmailRepository;
import zjg.marketplace.infrastructure.repository.interfaces.UserReactiveMongoRepository;

@Repository
public class QueryUserByEmailRepository implements QueryByEmailRepository<User> {
    private final UserReactiveMongoRepository jpaRepository;

    public QueryUserByEmailRepository(UserReactiveMongoRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Mono<User> find(String email) {
        return jpaRepository.findByEmail(email);
    }
}
