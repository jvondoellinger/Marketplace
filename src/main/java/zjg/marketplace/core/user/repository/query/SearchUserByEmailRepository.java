package zjg.marketplace.core.user.repository.query;

import reactor.core.publisher.Mono;

public interface SearchUserByEmailRepository<User> {
    Mono<User> find(String email);
}
