package zjg.marketplace.presentation.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.user.UserInput;
import zjg.marketplace.application.helper.AuthorizationTokenHelper;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.CreateService;
import zjg.marketplace.application.service.promisse.DeleteService;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.application.service.promisse.UpdateService;
import zjg.marketplace.core.user.entity.User;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final FindService<User> findService;
    private final DeleteService<User> deleteService;
    private final CreateService<User, UserInput> createService;
    private final UpdateService<User, UserInput> updateService;
    private final AuthorizationTokenHelper helper;

    public UserController(ServiceResolverFacade resolverFacade, AuthorizationTokenHelper helper) {
        this.findService = resolverFacade.resolveFind(User.class);
        this.deleteService = resolverFacade.resolveDelete(User.class);
        this.createService = resolverFacade.resolveCreate(User.class, UserInput.class);
        this.updateService = resolverFacade.resolveUpdate(User.class, UserInput.class);
        this.helper = helper;
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public Flux<User> get(
            @RequestParam(defaultValue = "0") Long offset,
            @RequestParam(defaultValue = "10") Integer max) {
        return findService.get(offset, max);
    }

    @GetMapping("/id")
    //@PreAuthorize("hasRole('USER')")
    public Mono<User> getById(ServerWebExchange exchange) {
        var token = helper.getDecryptedTokenOrThrow(exchange);
        return findService.findById(token.getUserId());
    }

    /// Needs ID and Fields (username, email and password to update!)
    @PatchMapping("/{id}")
    //@PreAuthorize("hasRole('USER')")
    public Mono<User> update(@RequestBody UserInput partialUser, @PathVariable String id) {
        return updateService.update(partialUser, id);
    }
    @DeleteMapping
    //@PreAuthorize("hasRole('USER')")
    public Mono<Void> delete(ServerWebExchange exchange) {
        var token = helper.getDecryptedTokenOrThrow(exchange);
        return deleteService.deleteById(token.getUserId());
    }

}
