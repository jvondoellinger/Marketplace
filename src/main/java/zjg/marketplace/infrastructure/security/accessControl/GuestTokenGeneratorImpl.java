package zjg.marketplace.infrastructure.security.accessControl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.security.access.GuestAccess;
import zjg.marketplace.core.security.access.RoleAccess;
import zjg.marketplace.core.security.models.Token;
import zjg.marketplace.core.security.services.TokenAuthenticator;
import zjg.marketplace.core.security.services.accessControll.GuestTokenGenerator;

@Service
public class GuestTokenGeneratorImpl implements GuestTokenGenerator {
      private final TokenAuthenticator tokenAuthenticator;

      public GuestTokenGeneratorImpl(TokenAuthenticator tokenAuthenticator) {
            this.tokenAuthenticator = tokenAuthenticator;
      }

      @Override
      public Mono<Token.EncryptedToken> generate() {
            return Mono.just(tokenAuthenticator.encrypt("guest", new GuestAccess()));
      }
}
