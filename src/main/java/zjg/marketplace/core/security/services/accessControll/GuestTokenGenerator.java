package zjg.marketplace.core.security.services.accessControll;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.security.models.Token;

public interface GuestTokenGenerator {
      Mono<Token.EncryptedToken> generate();
}
