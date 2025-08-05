package zjg.marketplace.application.service.user;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IFindService;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.interfaces.services.repository.IRepository;

@Service
public class FindUserService implements IFindService<User> {
    private final IRepository<User> repository;

    public FindUserService(IRepository<User> repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(value = "user", key = "#offset + '-' + #limit")
    public Flux<User> get(Long offset, Integer limit) {
        return repository.findWithPagination(offset, limit);
    }

    @Override
    @Cacheable(value = "user", key = "#id")
    public Mono<User> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<User> findByIdNoCache(String id) {
        return repository.findById(id);
    }
}
