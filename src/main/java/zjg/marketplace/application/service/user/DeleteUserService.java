package zjg.marketplace.application.service.user;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.DeleteService;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.user.entity.User;

@Service
public class DeleteUserService implements DeleteService<User> {
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
