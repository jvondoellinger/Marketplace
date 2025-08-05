package zjg.marketplace.core.interfaces.services.security;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.user.UserCredentials;
import zjg.marketplace.core.exceptions.security.user.EmailNotExistsException;
import zjg.marketplace.core.exceptions.security.user.IncorrectPasswordProvidedException;
import zjg.marketplace.core.valueObjects.security.Token;

public interface UserAuthenticator {
    Mono<Token.EncryptedToken> login(UserCredentials credentials) throws IncorrectPasswordProvidedException, EmailNotExistsException;
}
