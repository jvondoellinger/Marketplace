package zjg.marketplace.application.service.promisse;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.exceptions.security.user.EmailNotExistsException;

public interface IFindByEmail<TEntity> {
    Mono<TEntity> find(String email) throws EmailNotExistsException;
}
