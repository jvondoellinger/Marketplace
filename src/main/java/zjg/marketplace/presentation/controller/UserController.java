package zjg.marketplace.presentation.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.user.UserInput;
import zjg.marketplace.application.dto.user.UserUpdateInput;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.resolver.services.impl.CreateResolver;
import zjg.marketplace.application.resolver.services.impl.DeleteResolver;
import zjg.marketplace.application.resolver.services.impl.FindResolver;
import zjg.marketplace.application.resolver.services.impl.UpdateResolver;
import zjg.marketplace.application.service.promisse.ICreateService;
import zjg.marketplace.application.service.promisse.IDeleteService;
import zjg.marketplace.application.service.promisse.IFindService;
import zjg.marketplace.application.service.promisse.IUpdateService;
import zjg.marketplace.core.entity.user.User;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IFindService<User> findService;
    private final IDeleteService<User> deleteService;
    private final ICreateService<User, UserInput> createService;
    private final IUpdateService<User, UserInput> updateService;

    public UserController(ServiceResolverFacade resolverFacade) {
        this.findService = resolverFacade.resolveFind(User.class);
        this.deleteService = resolverFacade.resolveDelete(User.class);
        this.createService = resolverFacade.resolveCreate(User.class, UserInput.class);
        this.updateService = resolverFacade.resolveUpdate(User.class, UserInput.class);
    }

    @GetMapping
    public Flux<User> get(
            @RequestParam(defaultValue = "0") Long offset,
            @RequestParam(defaultValue = "10") Integer max) {
        return findService.get(offset, max);
    }

    @GetMapping("/{id}")
    public Mono<User> getById(@PathVariable String id) {
        return findService.findById(id);
    }

    /// Needs ID and Fields (username, email and password to update!)
    @PatchMapping("/{id}")
    public Mono<User> update(@RequestBody UserInput partialUser, @PathVariable String id) {
        return updateService.update(partialUser, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return deleteService.deleteById(id);
    }

}
