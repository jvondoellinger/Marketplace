package zjg.marketplace.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.security.models.Token;
import zjg.marketplace.core.security.services.accessControll.GuestTokenGenerator;

@RestController
@RequestMapping("/api/guest")
public class GuestUserController {
      private final GuestTokenGenerator tokenGenerator;

      public GuestUserController(GuestTokenGenerator tokenGenerator) {
            this.tokenGenerator = tokenGenerator;
      }

      @GetMapping
      public Mono<Token.EncryptedToken> generateGuestToken() {
            return tokenGenerator.generate();
      }
}
