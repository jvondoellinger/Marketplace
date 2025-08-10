package zjg.marketplace.application.service.user;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IDeleteService;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.interfaces.services.repository.Repository;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;

@Service
public class DeleteUserService implements IDeleteService<User> {
    private final CommandRepository<User> command;

    public DeleteUserService(CommandRepository<User> command) {
        this.command = command;
    }

    @Override
    @CacheEvict(value = "user", key = "id")
    public Mono<Void> delete(User user) {
        return command.delete(user.getId());
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return command.delete(id);
    }
}
