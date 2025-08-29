package zjg.marketplace.infrastructure.security.accessControl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.security.access.AdminAccess;
import zjg.marketplace.core.security.access.GuestAccess;
import zjg.marketplace.core.security.exceptions.InvalidTokenException;
import zjg.marketplace.core.security.models.Token;
import zjg.marketplace.core.security.services.TokenAuthenticator;
import zjg.marketplace.core.security.services.accessControll.AdminTokenGenerator;
import zjg.marketplace.core.utils.StringUtils;
import zjg.marketplace.infrastructure.security.config.ApiKeyConfig;

@Service
public class AdminTokenGeneratorImpl implements AdminTokenGenerator {
      private final TokenAuthenticator tokenAuthenticator;
      private final ApiKeyConfig apiKeyConfig;
      public AdminTokenGeneratorImpl(TokenAuthenticator tokenAuthenticator, ApiKeyConfig apiKeyConfig) {
            this.tokenAuthenticator = tokenAuthenticator;
            this.apiKeyConfig = apiKeyConfig;
      }

      @Override
      public Mono<Token.EncryptedToken> generate(String apiKey) {
            if(apiKeyConfig.getApiKey().contentEquals(apiKey)) {
                  return Mono.just(tokenAuthenticator.encrypt("admin", new AdminAccess()));
            }
            else {
                  return Mono.error(new InvalidTokenException("Invalid API KEY provided!"));
            }
      }
}
