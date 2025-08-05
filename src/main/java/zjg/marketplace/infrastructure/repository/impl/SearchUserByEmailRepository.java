package zjg.marketplace.infrastructure.repository.impl;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.interfaces.services.repository.SearchByEmailRepository;
import zjg.marketplace.infrastructure.repository.spring.UserReactiveMongoRepository;

@Repository
public class SearchUserByEmailRepository implements SearchByEmailRepository<User> {
    public final UserReactiveMongoRepository repositoryLib;;

    public SearchUserByEmailRepository(UserReactiveMongoRepository repositoryLib) {
        this.repositoryLib = repositoryLib;
    }

    @Override
    public Mono<User> find(String email) {
        return repositoryLib.findByEmail(email);
    }
}
