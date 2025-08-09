package zjg.marketplace.application.service.user;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IDeleteService;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.interfaces.services.repository.Repository;

@Service
public class DeleteUserService implements IDeleteService<User> {
    private final Repository<User> repository;

    public DeleteUserService(Repository<User> repository) {
        this.repository = repository;
    }

    @Override
    @CacheEvict(value = "user", key = "id")
    public Mono<Void> delete(User user) {
        return repository.delete(user.getId());
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.delete(id);
    }
}
