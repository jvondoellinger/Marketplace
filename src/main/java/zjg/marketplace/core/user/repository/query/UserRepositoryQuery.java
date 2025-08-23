package zjg.marketplace.core.user.repository.query;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.interfaces.services.repository.query.QueryRepository;
import zjg.marketplace.core.user.entity.User;

public interface UserRepositoryQuery extends QueryRepository<User> {
    Mono<User> find(String email);
}
