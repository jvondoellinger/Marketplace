package zjg.marketplace.infrastructure.security.accessControl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IFindByEmail;
import zjg.marketplace.core.security.access.UserAccess;
import zjg.marketplace.core.user.repository.exceptions.EmailNotExistsException;
import zjg.marketplace.core.user.auth.exceptions.IncorrectPasswordProvidedException;
import zjg.marketplace.core.security.services.TextEncryptor;
import zjg.marketplace.core.security.services.TokenAuthenticator;
import zjg.marketplace.core.security.services.accessControll.UserTokenGenerator;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.user.entity.UserCredentials;
import zjg.marketplace.core.security.access.RoleAccess;
import zjg.marketplace.core.security.models.Token;

@Service
public class UserTokenGeneratorImpl implements UserTokenGenerator {
    private final IFindByEmail<User> findService;
      private final TokenAuthenticator tokenAuthenticator;
    private final TextEncryptor encryptor;
    public UserTokenGeneratorImpl(IFindByEmail<User> findService, TokenAuthenticator tokenAuthenticator, TextEncryptor encryptor) {
        this.findService = findService;
        this.tokenAuthenticator = tokenAuthenticator;
        this.encryptor = encryptor;
    }


    @Override
    public Mono<Token.EncryptedToken> generate(UserCredentials credentials) throws IncorrectPasswordProvidedException, EmailNotExistsException {
        return findService.find(credentials.getEmail())
                .map(user -> {
                    encryptor.throwIfInvalid(user.getPassword(), credentials.getPassword());
                      return tokenAuthenticator.encrypt(user.getId(), new UserAccess());
                });
    }
}
