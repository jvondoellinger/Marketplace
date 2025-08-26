package zjg.marketplace.core.security.services.accessControll;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.user.repository.exceptions.EmailNotExistsException;
import zjg.marketplace.core.user.auth.exceptions.IncorrectPasswordProvidedException;
import zjg.marketplace.core.user.entity.UserCredentials;
import zjg.marketplace.core.security.models.Token;

public interface UserTokenGenerator {
    Mono<Token.EncryptedToken> generate(UserCredentials credentials) throws IncorrectPasswordProvidedException, EmailNotExistsException;
}
