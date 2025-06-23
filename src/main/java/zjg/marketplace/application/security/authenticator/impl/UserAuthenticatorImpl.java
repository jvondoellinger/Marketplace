package zjg.marketplace.application.security.authenticator.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IFindByEmail;
import zjg.marketplace.application.service.user.FindUserByEmailService;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.entity.user.UserCredentials;
import zjg.marketplace.core.exceptions.security.user.EmailNotExistsException;
import zjg.marketplace.core.exceptions.security.user.IncorrectPasswordProvidedException;
import zjg.marketplace.core.interfaces.services.security.TextEncryptor;
import zjg.marketplace.core.interfaces.services.security.TokenAuthenticator;
import zjg.marketplace.core.interfaces.services.security.UserAuthenticator;
import zjg.marketplace.core.valueObject.security.Token;

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
                    return tokenAuthenticator.generate(user.getId());
                });
    }
}
