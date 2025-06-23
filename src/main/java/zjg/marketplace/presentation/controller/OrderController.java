package zjg.marketplace.presentation.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.order.OrderInput;
import zjg.marketplace.application.dto.order.OrderUpdateInput;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.*;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.entity.payment.PixPayment;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.mediator.OrderToPaymentMediator;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final ICreateService<Order, OrderInput> createService;
    private final IUpdateService<Order, OrderUpdateInput> updateService;
    private final IDeleteService<Order> deleteService;
    private final IFindService<Order> findService;
    private final IFindService<User> userFindService;
    private final IFindByUserId<Order> findByUserIdService;
    private final OrderToPaymentMediator mediator;
    public OrderController(ServiceResolverFacade facade, OrderToPaymentMediator mediator) {
        this.createService = facade.resolveCreate(Order.class, OrderInput.class);
        this.updateService = facade.resolveUpdate(Order.class, OrderUpdateInput.class);
        this.deleteService = facade.resolveDelete(Order.class);
        this.findService = facade.resolveFind(Order.class);
        this.userFindService = facade.resolveFind(User.class);
        this.findByUserIdService = facade.resolveFindByUserId(Order.class);
        this.mediator = mediator;
    }

    // --------------------------------------------------------------
    @GetMapping
    public Flux<Order> getAll(@RequestParam(defaultValue = "0") Long offset,
                              @RequestParam(defaultValue = "10") Integer limit) {
        return findService.get(offset, limit);
    }
    @GetMapping("/{orderId}")
    public Mono<Order> findOrder(@PathVariable String orderId) {
        return findService.findById(orderId);
    }

    @GetMapping("/user/{userId}")
    public Flux<Order> findOrdersByUserId(@PathVariable String userId) {
        return findByUserIdService.findByUserId(userId);
    }

    @PostMapping("/{userId}")
    public Mono<Order> createOrder(@RequestBody OrderInput input) {
        return createService.create(input);
    }

    @PutMapping("/{userId}")
    public Mono<Order> updateOrder(@RequestBody OrderUpdateInput input,
                                   @PathVariable String userId) {
        return updateService.update(input, userId);
    }
    @DeleteMapping("/{orderId}")
    public Mono<Void> delete(@RequestBody String orderId) {
        return deleteService.deleteById(orderId);
    }

    @PutMapping("/close/{orderId}")
    public Mono<Order> closeOrder(@RequestBody OrderUpdateInput input, @PathVariable String orderId) {
        return updateService.update(input, orderId);
    }

    // Pay

    @PostMapping("/pay/{orderId}")
    // @RequestParam("method") String method
    public Mono<PixPayment> generatePayment(@PathVariable String orderId) {
        return findService.findById(orderId)
                .flatMap(o ->
                    userFindService.findById(o.getBuyerId())
                            .flatMap(u -> mediator.orderToPayment(o, u))
                );
    }
}
