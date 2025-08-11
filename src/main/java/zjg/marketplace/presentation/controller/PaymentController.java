package zjg.marketplace.presentation.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.IFindService;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.entity.payment.PixPayment;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.mediator.OrderToPaymentMediator;

import java.util.Objects;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final OrderToPaymentMediator mediator;
    private final IFindService<Order> orderFindService;
    private final IFindService<User> userFindService;

    public PaymentController(OrderToPaymentMediator mediator, ServiceResolverFacade facade) {
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
