package zjg.marketplace.infrastructure.repository.adapter;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.interfaces.services.repository.Repository;
import zjg.marketplace.infrastructure.repository.interfaces.UserReactiveMongoRepository;

public class UserRepository implements Repository<User> {
    public final UserReactiveMongoRepository repositoryLib;
    private final ReactiveMongoTemplate template;
    public UserRepository(UserReactiveMongoRepository repositoryLib, ReactiveMongoTemplate template) {
        this.repositoryLib = repositoryLib;
        this.template = template;
    }

    @Override
    public Mono<User> insert(User entity) {
        return repositoryLib.insert(entity);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repositoryLib.deleteById(id);
    }

    @Override
    public Mono<User> findById(String id) {
        return repositoryLib.findById(id);
    }

    @Override
    public Flux<User> findWithPagination(Long offset, Integer limit) {
        Query query = new Query()
                .skip(offset)
                .limit(limit);
        return template.find(query, User.class);
    }
    @Override
    public Mono<User> update(User updated) {
        return repositoryLib.save(updated);
    }
}
