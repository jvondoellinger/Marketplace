package zjg.marketplace.application.utils;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import zjg.marketplace.core.exceptions.security.token.BlankTokenException;
import zjg.marketplace.core.exceptions.security.token.NullTokenException;

public class AuthorizationTokenUtils {
    private AuthorizationTokenUtils() {}

    public static String getAuthorizationToken(ServerWebExchange exchange) {
        var bearer = "Bearer ";
        var length = bearer.length();
        var token = exchange.getRequest().getHeaders().getFirst("Authorization");
        return token.contains(bearer) ? token.substring(length) : token;
    }
    public static String getAuthorizationTokenOrThrow(ServerWebExchange exchange) throws NullTokenException {
        var token = getAuthorizationToken(exchange);
        if (token == null) throw new NullTokenException();
        if (token.isBlank()) throw new BlankTokenException();
        return token;
    }
}
