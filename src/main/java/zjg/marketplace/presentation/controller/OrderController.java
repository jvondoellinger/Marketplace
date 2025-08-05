package zjg.marketplace.presentation.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.order.OrderInput;
import zjg.marketplace.application.dto.order.OrderUpdateInput;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.security.authenticator.impl.TokenService;
import zjg.marketplace.application.service.promisse.*;
import zjg.marketplace.application.utils.AuthorizationTokenUtils;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.valueObjects.security.Token;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    // Criar uma strategy para adicionar segurança!
    // Strategy para validar se o token é valido
    // Armazenar esses tokens no REDIS e utiliza-lo como controle de acesso (permitindo somente 1 (ip ou agent - celular, pc, etc) de login por usuario; bloqueando a conta temporariamente (até o usuario permitir o acesso deste novo dispositivo)

    private final ICreateService<Order, OrderInput> createService;
    private final IUpdateService<Order, OrderUpdateInput> updateService;
    private final IDeleteService<Order> deleteService;
    private final IFindService<Order> findService;
    private final IFindByUserId<Order> findByUserIdService;
    public OrderController(ServiceResolverFacade facade ) {
        this.createService = facade.resolveCreate(Order.class, OrderInput.class);
        this.updateService = facade.resolveUpdate(Order.class, OrderUpdateInput.class);
        this.deleteService = facade.resolveDelete(Order.class);
        this.findService = facade.resolveFind(Order.class);
        this.findByUserIdService = facade.resolveFindByUserId(Order.class);
    }

    // * GET --------------------------------------------------------------
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Flux<Order> getAll(@RequestParam(defaultValue = "0") Long offset, @RequestParam(defaultValue = "10") Integer limit) {
        return findService.get(offset, limit);
    }
    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/{orderId}")
    public Mono<Order> findOrder(@PathVariable String orderId) {
        return findService.findById(orderId);
    }

    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}")
    public Flux<Order> findOrdersByUserId(@PathVariable String userId) {
        return findByUserIdService.findByUserId(userId);
    }

    // * POST --------------------------------------------------------------

    //@PreAuthorize("hasRole('USER')")
    @PostMapping
    public Mono<Order> createOrder(@RequestBody OrderInput input) {
        return createService.create(input);
    }

    // * PUT --------------------------------------------------------------

    @PutMapping
    //@PreAuthorize("hasRole('USER')")
    public Mono<Order> updateOrder(@RequestBody OrderUpdateInput input) {
        return updateService.update(input, input.getUserId());
    }

    //@PreAuthorize("hasRole('USER')")
    @PutMapping("/close/{orderId}")
    public Mono<Order> closeOrder(@RequestBody OrderUpdateInput input, @PathVariable String orderId) {
        return updateService.update(input, orderId);
    }

    // * DELETE --------------------------------------------------------------

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{orderId}")
    public Mono<Void> delete(@RequestBody String orderId) {
        return deleteService.deleteById(orderId);
    }


}
