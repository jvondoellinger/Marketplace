package zjg.marketplace.application.service.user;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IFindByEmail;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.exceptions.security.user.EmailNotExistsException;
import zjg.marketplace.core.interfaces.services.repository.query.QueryByEmailRepository;

@Service
public class FindUserByEmailService implements IFindByEmail<User> {
private final QueryByEmailRepository<User> query;

    public FindUserByEmailService(QueryByEmailRepository<User> query) {
        this.query = query;
    }

    @Override
    // @Cacheable(value = "user", key = "userCredentials.email") Não faz sentido, pois o REDIS expira em 5min e o token vai expirar em 1h
    public Mono<User> find(String email) throws EmailNotExistsException {
        return query.find(email)
                .switchIfEmpty(Mono.error(new EmailNotExistsException()));
    }
}
