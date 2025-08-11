package zjg.marketplace.application.security.filter;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.helper.AuthorizationTokenHelper;
import zjg.marketplace.application.utils.AuthorizationTokenUtils;
import zjg.marketplace.core.exceptions.validations.token.ExpiredTokenException;
import zjg.marketplace.core.exceptions.validations.token.InvalidTokenException;
import zjg.marketplace.core.interfaces.services.logging.Logger;
import zjg.marketplace.core.interfaces.services.security.TokenAuthenticator;

import java.util.List;

//@Service
public class JwtJweAuthWebFilter implements WebFilter {
    private final TokenAuthenticator authenticator;
    private final Logger logger;
    private final AuthorizationTokenHelper helper;
    public JwtJweAuthWebFilter(TokenAuthenticator authenticator, Logger logger, AuthorizationTokenHelper helper) {
        this.authenticator = authenticator;
        this.logger = logger;
        this.helper = helper;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        var path = exchange.getRequest().getURI().getPath();
        if (path.startsWith("/api/public") || path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui") || path.startsWith("/webjars")) {
            return chain.filter(exchange);
        }
        var ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        var token = AuthorizationTokenUtils.getAuthorizationToken(exchange);
        if(token != null && token.startsWith("Bearer ")) {
            try {
                var cleanToken = token.substring("Bearer ".length());
                var decryptedToken = authenticator.decrypt(cleanToken);
                var auth = new UsernamePasswordAuthenticationToken(decryptedToken.getUserId(),
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_%s".formatted(decryptedToken.getRole()))));
                return chain.filter(exchange)
                        .contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));
            } catch (InvalidTokenException | ExpiredTokenException e) {
                logger.warning(this.getClass(), "Access attempt denied due invalid token is provided! IP: %s".formatted(ip));
                return unauthorize(exchange);
            }
        }
        logger.warning(this.getClass(), STR."Access attempt denied due to lack of access token! IP: %s".formatted(ip));
        return unauthorize(exchange);

    }

    private Mono<Void> unauthorize(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
