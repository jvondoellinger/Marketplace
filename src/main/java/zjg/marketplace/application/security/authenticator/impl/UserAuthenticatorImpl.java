package zjg.marketplace.application.security.authenticator.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IFindByEmail;
import zjg.marketplace.core.user.repository.exceptions.EmailNotExistsException;
import zjg.marketplace.core.user.auth.exceptions.IncorrectPasswordProvidedException;
import zjg.marketplace.core.security.services.TextEncryptor;
import zjg.marketplace.core.security.services.TokenAuthenticator;
import zjg.marketplace.core.security.services.UserAuthenticator;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.user.entity.UserCredentials;
import zjg.marketplace.core.security.enums.RoleAccess;
import zjg.marketplace.core.security.models.Token;

@Service
public class UserAuthenticatorImpl implements UserAuthenticator {
    private final IFindByEmail<User> findService;
    private final TokenAuthenticator tokenAuthenticator;
    private final TextEncryptor encryptor;
    public UserAuthenticatorImpl(IFindByEmail<User> findService, TokenAuthenticator tokenAuthenticator, TextEncryptor encryptor) {
        this.findService = findService;
        this.tokenAuthenticator = tokenAuthenticator;
        this.encryptor = encryptor;
    }


    @Override
    public Mono<Token.EncryptedToken> login(UserCredentials credentials) throws IncorrectPasswordProvidedException, EmailNotExistsException {
        return findService.find(credentials.getEmail())
                .map(user -> {
                    encryptor.throwIfInvalid(user.getPassword(), credentials.getPassword());
                    return tokenAuthenticator.encrypt(user.getId(), RoleAccess.USER);
                });
    }
}
