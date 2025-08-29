package zjg.marketplace.presentation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.security.models.Token;
import zjg.marketplace.core.security.services.accessControll.AdminTokenGenerator;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
      private final AdminTokenGenerator tokenGenerator;

      public AdminController(AdminTokenGenerator tokenGenerator) {
            this.tokenGenerator = tokenGenerator;
      }

      @PostMapping
      public Mono<Token.EncryptedToken> get(@RequestBody Token.EncryptedToken apiKey) {
            return tokenGenerator.generate(apiKey.getToken());
      }
}
