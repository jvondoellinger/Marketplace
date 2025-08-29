package zjg.marketplace.presentation.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.resolver.facade.ServiceResolver;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.payment.entity.PixPayment;
import zjg.marketplace.core.order.facade.OrderPaymentProcessor;
import zjg.marketplace.core.user.entity.User;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final OrderPaymentProcessor mediator;
    private final FindService<Order> orderFindService;
    private final FindService<User> userFindService;

    public PaymentController(OrderPaymentProcessor mediator, ServiceResolver facade) {
        this.mediator = mediator;
        this.orderFindService = facade.resolveFind(Order.class);
        this.userFindService = facade.resolveFind(User.class);
    }

    @GetMapping("/pix/{orderId}")
    public Mono<PixPayment> generatePayment(@PathVariable String orderId) {
        return orderFindService.findById(orderId)
                .flatMap(o ->
                     userFindService.findById(o.getBuyerId()).zipWith(Mono.just(o))
                )
                .switchIfEmpty(Mono.error(new RuntimeException("Não encontrado")))
                .flatMap(t -> {
                    return mediator.orderToPayment(t.getT2(), t.getT1());
                });
    }
}
