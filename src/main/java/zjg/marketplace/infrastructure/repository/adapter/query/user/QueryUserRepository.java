package zjg.marketplace.infrastructure.repository.adapter.query.user;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.interfaces.services.repository.query.QueryRepository;
import zjg.marketplace.infrastructure.repository.interfaces.UserReactiveMongoRepository;

@Repository
public class QueryUserRepository implements QueryRepository<User> {
    private final UserReactiveMongoRepository jpaRepository;
    private final ReactiveMongoTemplate template;

    public QueryUserRepository(UserReactiveMongoRepository jpaRepository, ReactiveMongoTemplate template) {
        this.jpaRepository = jpaRepository;
        this.template = template;
    }

    @Override
    public Mono<User> findById(String id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Flux<User> findWithPagination(Long offset, Integer limit) {
        var query = new Query().skip(offset).limit(limit);
        return template.find(query, User.class);
    }
}
