package zjg.marketplace.application.service.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.api.input.UserInput;
import zjg.marketplace.core.entity.User;
import zjg.marketplace.application.mapper.UserMapper;
import zjg.marketplace.repository.UserRepository;
import zjg.marketplace.application.service.promisse.IBasicService;

@Service
public class UserService implements IBasicService<User, UserInput> {
    private final UserRepository repository;
    private final UserMapper mapper;
    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<User> create(UserInput input) {
        var mapped = mapper.map(input);
        return repository.save(mapped);
    }

    @Override
    public Mono<User> update(UserInput input, String identifier) {
        return findById(identifier).flatMap(original -> {
            var mapped = mapper.map(input);
            var user = original.updateFrom(mapped);
            return repository.save(user);
        });
    }

    @Override
    public Mono<Void> delete(String identifier) {
        return repository.deleteById(identifier);
    }
    @Override
    public Mono<User> findById(String identifier) {
        return repository.findById(identifier);
    }
    @Override
    public Flux<User> get(Long offset, Integer limit) {
        return repository.findWithPagination(offset, limit);
    }

}
