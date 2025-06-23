package zjg.marketplace.presentation.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.presentation.input.UserInput;
import zjg.marketplace.application.service.impl.UserService;
import zjg.marketplace.core.entity.user.User;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public Mono<List<User>> get(
            @RequestParam(defaultValue = "0") Long offset,
            @RequestParam(defaultValue = "10") Integer max) {
        return service.get(offset, max);
    }
    @GetMapping("{id}")
    public Mono<User> getById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public Mono<User> create(@RequestBody UserInput dto) {
        return service.create(dto);
    }

    /// Needs ID and Fields (username, email and password to update!)
    @PatchMapping("{id}")
    public Mono<User> update(@RequestBody UserInput partialUser, @PathVariable String id) {
        return service.update(partialUser, id);
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }

}
