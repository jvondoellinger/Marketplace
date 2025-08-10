package zjg.marketplace.infrastructure.repository.adapter.command.user;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.infrastructure.repository.interfaces.UserReactiveMongoRepository;

@Repository
public class CommandUserRepository implements CommandRepository<User> {
    private final UserReactiveMongoRepository jpaRepository;

    public CommandUserRepository(UserReactiveMongoRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Mono<User> insert(User entity) {
        return jpaRepository.insert(entity);
    }

    @Override
    public Mono<User> update(User updated) {
        return jpaRepository.save(updated);
    }

    @Override
    public Mono<Void> delete(String id) {
        return jpaRepository.deleteById(id);
    }
}
