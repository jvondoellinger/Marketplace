package zjg.marketplace.application.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.interfaces.repository.IRepository;
import zjg.marketplace.presentation.input.UserInput;
import zjg.marketplace.application.mapper.UserMapper;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.interfaces.security.TextEncryptor;
import zjg.marketplace.infrastructure.repository.spring.UserReactiveMongoRepository;

import java.util.List;

@Service
public class UserService {
    private final IRepository<User> repository;
    private final UserMapper mapper;
    private final TextEncryptor encryptor;
    public UserService(IRepository<User> repository, UserMapper mapper, TextEncryptor encryptor) {
        this.repository = repository;
        this.mapper = mapper;
        this.encryptor = encryptor;
    }

    public Mono<User> create(UserInput input) {
        var mapped = mapper.map(input, encryptor);
        return repository.insert(mapped);
    }

    public Mono<User> update(UserInput input, String identifier) {
        var mapped = mapper.partialMap(input, encryptor);
        return findById(identifier).flatMap(original -> {
            var user = original.selfUpdate(mapped);
            return repository.update(user);
        });
    }

    public Mono<Void> delete(String identifier) {
        return repository.delete(identifier);
    }

    public Mono<User> findById(String identifier) {
        return repository.findById(identifier).map(User::clearPassword);
    }

    public Mono<List<User>> get(Long offset, Integer limit) {
        return repository.findWithPagination(offset, limit)
                .map(User::clearPassword)
                .collectList();
    }

}
