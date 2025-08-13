package zjg.marketplace.application.service.user;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.interfaces.services.repository.query.QueryRepository;

@Service
public class FindUserService implements FindService<User> {
    private final QueryRepository<User> query;

    public FindUserService(QueryRepository<User> query) {
        this.query = query;
    }

    @Override
    @Cacheable(value = "user", key = "#offset + '-' + #limit")
    public Flux<User> get(long offset, int limit) {
        return query.findWithPagination(offset, limit);
    }

    @Override
    @Cacheable(value = "user", key = "#id")
    public Mono<User> findById(String id) {
        return query.findById(id);
    }

    @Override
    public Mono<User> findByIdNoCache(String id) {
        return query.findById(id);
    }
}
