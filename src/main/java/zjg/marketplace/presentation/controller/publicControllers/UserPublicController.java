package zjg.marketplace.presentation.controller.publicControllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.user.UserInput;
import zjg.marketplace.application.service.promisse.CreateService;
import zjg.marketplace.core.security.services.UserAuthenticator;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.user.entity.UserCredentials;
import zjg.marketplace.core.security.models.Token;

@RestController
@RequestMapping("/api/public/user")
public class UserPublicController {
    private final CreateService<User, UserInput> createService;
    private final UserAuthenticator authenticator;
    public UserPublicController(CreateService<User, UserInput> createService, UserAuthenticator authenticator) {
        this.createService = createService;
        this.authenticator = authenticator;
    }

    @PostMapping("/register")
    public Mono<User> create(@RequestBody UserInput dto) {
        return createService.create(dto);
    }

    @PostMapping("/login")
    public Mono<Token.EncryptedToken> login(@RequestBody UserCredentials credentials) {
        return authenticator.login(credentials);
    }
}
