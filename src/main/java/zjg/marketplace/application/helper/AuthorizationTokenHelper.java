package zjg.marketplace.application.helper;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import zjg.marketplace.application.utils.AuthorizationTokenUtils;
import zjg.marketplace.core.security.exceptions.NullTokenException;
import zjg.marketplace.core.security.exceptions.ExpiredTokenException;
import zjg.marketplace.core.security.exceptions.InvalidTokenException;
import zjg.marketplace.core.security.services.TokenAuthenticator;
import zjg.marketplace.core.security.models.Token;

import java.util.Objects;

@Service
public class AuthorizationTokenHelper {
    private final TokenAuthenticator service;

    public AuthorizationTokenHelper(TokenAuthenticator service) {
        this.service = service;
    }

    public Token.DecryptedToken getDecryptedToken(ServerWebExchange exchange) throws InvalidTokenException, ExpiredTokenException {
        var token = AuthorizationTokenUtils.getAuthorizationTokenOrThrow(exchange);
        return service.decrypt(token);
    }
    public Token.DecryptedToken getDecryptedTokenOrThrow(ServerWebExchange exchange) throws NullTokenException, InvalidTokenException {
        var token = getDecryptedToken(exchange);
        if (Objects.isNull(token)) throw new NullTokenException();
        if (Objects.isNull(token.getUserId())) throw new InvalidTokenException();
        if (Objects.isNull(token.getRole())) throw new InvalidTokenException();
        if (Objects.isNull(token.getExpireAt())) throw new InvalidTokenException();
        if (token.getUserId().isBlank()) throw new InvalidTokenException();
        return token;
    }
}
