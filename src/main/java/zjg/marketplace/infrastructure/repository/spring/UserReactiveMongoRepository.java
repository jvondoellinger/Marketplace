package zjg.marketplace.infrastructure.repository.spring;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.user.User;

@Repository
public interface UserReactiveMongoRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByEmail(String email);
}
