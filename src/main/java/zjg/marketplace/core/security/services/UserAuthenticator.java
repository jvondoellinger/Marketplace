package zjg.marketplace.core.security.services;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.user.repository.exceptions.EmailNotExistsException;
import zjg.marketplace.core.user.auth.exceptions.IncorrectPasswordProvidedException;
import zjg.marketplace.core.user.entity.UserCredentials;
import zjg.marketplace.core.security.models.Token;

public interface UserAuthenticator {
    Mono<Token.EncryptedToken> login(UserCredentials credentials) throws IncorrectPasswordProvidedException, EmailNotExistsException;
}
